package speedhome.interview.boot;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import speedhome.interview.boot.model.Member;
import speedhome.interview.boot.repository.MemberRepository;
import speedhome.interview.boot.service.MemberService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberServiceTests {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddMember() {
        Member member = new Member();
        member.setName("Test Member");
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member result = memberService.addMember(member);

        assertEquals(member, result);
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void testUpdateMember() {
        Member member = new Member();
        member.setId(1L);
        member.setName("Updated Member");
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member result = memberService.updateMember(member);

        assertEquals(member, result);
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void testDeleteMember() {
        Long memberId = 1L;

        memberService.deleteMember(memberId);

        verify(memberRepository, times(1)).deleteById(memberId);
    }

    @Test
    public void testGetMemberById() {
        Long memberId = 1L;
        Member member = new Member();
        member.setId(memberId);
        member.setName("Test Member");
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        Optional<Member> result = memberService.getMemberById(memberId);

        assertTrue(result.isPresent());
        assertEquals(member, result.get());
        verify(memberRepository, times(1)).findById(memberId);
    }

    @Test
    public void testGetAllMembers() {
        Member member1 = new Member();
        member1.setName("Member 1");

        Member member2 = new Member();
        member2.setName("Member 2");

        List<Member> members = Arrays.asList(member1, member2);
        when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();

        assertEquals(2, result.size());
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteByUsername() throws NotFoundException {
        String username = "testuser";
        Member member = new Member();
        member.setName(username);
        when(memberRepository.findByName(username)).thenReturn(member);

        memberService.deleteByUsername(username);

        verify(memberRepository, times(1)).findByName(username);
        verify(memberRepository, times(1)).delete(member);
    }

    @Test
    public void testDeleteByUsernameNotFound() {
        String username = "nonexistentuser";
        when(memberRepository.findByName(username)).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            memberService.deleteByUsername(username);
        });

        String expectedMessage = "Member not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(memberRepository, times(1)).findByName(username);
        verify(memberRepository, times(0)).delete(any(Member.class));
    }
}

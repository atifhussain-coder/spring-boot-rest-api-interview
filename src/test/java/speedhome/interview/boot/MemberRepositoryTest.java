package speedhome.interview.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import speedhome.interview.boot.model.Member;

import static org.junit.jupiter.api.Assertions.*;
import speedhome.interview.boot.repository.MemberRepository;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member("John Doe", "john.doe@example.com");
        memberRepository.save(member);
    }

    @Test
    public void testFindByName() {
        Member foundMember = memberRepository.findByName("John Doe");
        assertNotNull(foundMember);
        assertEquals("John Doe", foundMember.getName());
        assertEquals("john.doe@example.com", foundMember.getEmail());
    }

    @Test
    public void testFindByNameNotFound() {
        Member foundMember = memberRepository.findByName("Nonexistent User");
        assertNull(foundMember);
    }
}


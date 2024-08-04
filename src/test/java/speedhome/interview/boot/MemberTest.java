package speedhome.interview.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import speedhome.interview.boot.model.Member;

@DataJpaTest
public class MemberTest {

    @Autowired
    private TestEntityManager entityManager;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member("Test Member", "test@example.com");
    }

    @Test
    public void testPersist() {
        Member savedMember = entityManager.persistFlushFind(member);

        assertNotNull(savedMember.getId());
        assertNotNull(savedMember.getCreatedAt());
        assertNull(savedMember.getUpdatedAt());
        assertEquals("Test Member", savedMember.getName());
        assertEquals("test@example.com", savedMember.getEmail());
    }

    @Test
    public void testUpdate() {
        Member savedMember = entityManager.persistFlushFind(member);

        savedMember.setName("Updated Member");
        savedMember = entityManager.persistFlushFind(savedMember);

        assertNotNull(savedMember.getUpdatedAt());
        assertEquals("Updated Member", savedMember.getName());
    }
}


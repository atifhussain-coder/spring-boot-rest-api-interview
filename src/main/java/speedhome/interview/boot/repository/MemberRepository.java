package speedhome.interview.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import speedhome.interview.boot.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String username);
}

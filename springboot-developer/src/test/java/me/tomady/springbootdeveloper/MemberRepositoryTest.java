package me.tomady.springbootdeveloper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @Test
    void getAllMembers() {
        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberById() {
        Member member = memberRepository.findById(2L).get();

        assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberByName() {
        Member member = memberRepository.findByName("C").get();

        assertThat(member.getId()).isEqualTo(3);
    }
}
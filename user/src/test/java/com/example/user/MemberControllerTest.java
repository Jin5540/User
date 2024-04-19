package com.example.user;

import com.example.user.json.JsonUtils;
import com.example.user.member.domain.ListDTO;
import com.example.user.member.domain.MemberDTO;
import com.example.user.member.domain.MemberUpdateDTO;

import com.example.user.member.domain.SortCheck;
import com.example.user.member.domain.entity.Member;
import com.example.user.member.repository.MemberRepository;
import com.example.user.status.domain.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testJoin() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("test1");
        memberDTO.setPassword("qaz1111");
        memberDTO.setEmail("test@test.com");
        memberDTO.setName("테스터");
        memberDTO.setNickName("테스터");
        memberDTO.setPhoneNumber("010-1111-1234");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(memberDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ResponseDTO responseDTO = JsonUtils.fromJson(content, ResponseDTO.class);
        String utf8String = new String(responseDTO.getMessage().getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("CODE :: "+responseDTO.getCode()+", MESSAGE :: "+ utf8String);

    }

    @Test
    public void testList() throws Exception {
        Member member = new Member();

        member.setUserId("test1");
        member.setPassword("qaz1111");
        member.setEmail("test@test.com");
        member.setName("테스터");
        member.setNickName("테스터");
        member.setPhoneNumber("010-1111-1234");

        memberRepository.save(member);

        ListDTO listDTO = new ListDTO();
        listDTO.setPage(0);
        listDTO.setPageSize(10);
        listDTO.setSort(SortCheck.CREATED_AT);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(listDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // 결과 확인
        String content = new String(result.getResponse().getContentAsString().getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(content);
    }

    @Test
    public void testUpdate() throws Exception {

        Member member = new Member();

        member.setUserId("test1");
        member.setPassword("qaz1111");
        member.setEmail("test@test.com");
        member.setName("테스터");
        member.setNickName("테스터");
        member.setPhoneNumber("010-1111-1234");

        memberRepository.save(member);

        String userId = "test1";
        MemberUpdateDTO memberUpdateDTO = new MemberUpdateDTO();
        memberUpdateDTO.setName("개명테스터");
        memberUpdateDTO.setPassword("update1111");
        memberUpdateDTO.setNickName("테스터바꿈");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(memberUpdateDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ResponseDTO responseDTO = JsonUtils.fromJson(content, ResponseDTO.class);
        String utf8String = new String(responseDTO.getMessage().getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("CODE :: "+responseDTO.getCode()+", MESSAGE :: "+ utf8String);

    }
}



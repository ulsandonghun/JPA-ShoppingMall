package jpabook.jpashop.api;


import javax.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);//이 객체를 메세지 바디에 담아서
        // client에 넘겨줌. 또 이것이 바로 JSON 형식으로 변환되어서 client에 보여짐.


    }

   @PostMapping("/api/v2/members")
   public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest createMemberRequest) {


        Member member = new Member();
        member.setName(createMemberRequest.getName());
       Long id = memberService.join(member);

       return new CreateMemberResponse(id
       );



   }

    @Data
    public static class CreateMemberResponse {
        private Long id;
        String responsemessagebody="바로 response message body 에 DTO 객체가 꼽혀서 들어오는 것이다.";

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    public static class CreateMemberRequest {
        private String name;
        int a;
    }
}

package speedhome.interview.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import speedhome.interview.boot.model.Member;
import speedhome.interview.boot.service.MemberService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/web/members")
public class MemberWebController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String listMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "member_list"; // Thymeleaf template name (member_list.jsp)
    }

    @GetMapping("/new")
    public String createMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "member-form";
    }

    @PostMapping
    public String saveMember(@ModelAttribute("member") Member member) {
        memberService.addMember(member);
        return "redirect:/web/members";
    }

    @GetMapping("/edit/{id}")
    public String editMemberForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("member", memberService.getMemberById(id).orElse(new Member()));
        return "member-form";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute("member") Member member) {
        memberService.updateMember(member);
        return "redirect:/web/members";
    }

    @PostMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/web/members";
    }
}

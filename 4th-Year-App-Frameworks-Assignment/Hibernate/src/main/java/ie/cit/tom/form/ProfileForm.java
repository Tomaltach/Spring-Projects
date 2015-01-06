package ie.cit.tom.form;

import ie.cit.tom.entity.Member;
import ie.cit.tom.entity.User;

@SuppressWarnings("unused")
public class ProfileForm {
	private final User user;
	private final Member member;
	
	public ProfileForm() {
		this.user = new User();
		this.member = new Member();
	}
	public ProfileForm(User user, Member member) {
		this.user = user;
		this.member = member;
	}
}

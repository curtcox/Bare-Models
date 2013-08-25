package net.baremodels.common;

import net.baremodels.models.ObjectModel;

import java.util.List;

public final class User
{
    public boolean me;
    public String          firstName;
    public String          lastName;
    public String          userName;
    public PhoneNumber     cellPhone;
    public PhoneNumber     homePhone;
    public PhoneNumber     workPhone;
    public StreetAddress   streetAddress;
    public FacebookAccount facebook;
    public LinkedInAccount linkedIn;
    public TwitterHandle   twitter;
    public EmailAddress    email;
    public Avatar          avatar;
    public List<Team>      teams;
    public List<Skill>     skills;
    public List<Badge>     badges;

    public String fullName() {
        return firstName + " " + lastName;
    }
}

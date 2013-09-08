package net.baremodels.apps;

import net.baremodels.common.Badge;
import net.baremodels.common.Skill;
import net.baremodels.common.Team;
import net.baremodels.common.User;

import java.util.ArrayList;
import java.util.List;

public final class Nucleus {
    public List<Team> teams = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    public List<Badge> badges = new ArrayList<>();
    public List<Skill> skills = new ArrayList<>();
}

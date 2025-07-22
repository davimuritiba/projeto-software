import java.util.*;

public class GroupService
    {
        private List<Group> groups = new ArrayList<>();

        public Group createGroup(String name, String description, UUID ownerId)
            {
                Group group = new Group(name, description, ownerId);
                groups.add(group);
                return group;
            }

        public boolean addUserToGroup(UUID groupId, UUID userId)
                {
                    for (Group g : groups)
                        {
                            if (g.getId().equals(groupId))
                                {
                                    return g.addMember(userId);
                                }
                        }
                    return false;
                }

        public boolean removeUserFromGroup(UUID groupId, UUID userId)
            {
                for (Group g : groups)
                    {
                        if (g.getId().equals(groupId))
                            {
                                return g.removeMember(userId);
                            }
                    }
                return false;
            }

        public boolean addPostToGroup(UUID groupId, Post post)
            {
                for (Group g : groups)
                    {
                        if (g.getId().equals(groupId))
                            {
                                return g.addPost(post);
                            }
                    }
                return false;
            }

        public List<Group> getGroupsForUser(UUID userId)
            {
                List<Group> result = new ArrayList<>();
                for (Group g : groups)
                    {
                        if (g.getMembers().contains(userId))
                            {
                                result.add(g);
                            }
                    }
                return result;
            }

        public List<Group> getAllGroups()
            {
                return groups;
            }
    }

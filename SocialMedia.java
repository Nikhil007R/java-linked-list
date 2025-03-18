import java.util.HashSet;

class Friend {
    int friendId;
    Friend next;

    public Friend(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class User {
    int userId;
    String name;
    int age;
    Friend friendList; // Head of the friend list
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendList = null;
        this.next = null;
    }

    // Add a friend to the user's friend list
    public void addFriend(int friendId) {
        if (isFriend(friendId)) {
            System.out.println(name + " is already friends with User " + friendId);
            return;
        }

        Friend newFriend = new Friend(friendId);
        newFriend.next = friendList;
        friendList = newFriend;
    }

    // Remove a friend from the user's friend list
    public void removeFriend(int friendId) {
        if (friendList == null) {
            System.out.println(name + " has no friends.");
            return;
        }

        if (friendList.friendId == friendId) {
            friendList = friendList.next;
            return;
        }

        Friend prev = friendList, current = friendList.next;
        while (current != null) {
            if (current.friendId == friendId) {
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }

        System.out.println(name + " is not friends with User " + friendId);
    }

    // Check if a user is a friend
    public boolean isFriend(int friendId) {
        Friend temp = friendList;
        while (temp != null) {
            if (temp.friendId == friendId) return true;
            temp = temp.next;
        }
        return false;
    }

    // Display all friends of the user
    public void displayFriends() {
        System.out.print("Friends of " + name + " (ID: " + userId + "): ");
        if (friendList == null) {
            System.out.println("No friends added.");
            return;
        }

        Friend temp = friendList;
        while (temp != null) {
            System.out.print(temp.friendId + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Count the number of friends
    public int countFriends() {
        int count = 0;
        Friend temp = friendList;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class SocialMedia {
    private User head;

    public SocialMedia() {
        head = null;
    }

    // Add a new user
    public void addUser(int userId, String name, int age) {
        if (getUserById(userId) != null) {
            System.out.println("User ID " + userId + " already exists.");
            return;
        }

        User newUser = new User(userId, name, age);
        newUser.next = head;
        head = newUser;
    }

    // Get user by User ID
    public User getUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Get user by Name
    public User getUserByName(String name) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Add a friend connection between two users
    public void addFriendConnection(int userId1, int userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.addFriend(userId2);
        user2.addFriend(userId1);
    }

    // Remove a friend connection between two users
    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.removeFriend(userId2);
        user2.removeFriend(userId1);
    }

    // Find mutual friends between two users
    public void findMutualFriends(int userId1, int userId2) {
        User user1 = getUserById(userId1);
        User user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        HashSet<Integer> friends1 = new HashSet<>();
        Friend temp1 = user1.friendList;
        while (temp1 != null) {
            friends1.add(temp1.friendId);
            temp1 = temp1.next;
        }

        System.out.print("Mutual friends between " + user1.name + " and " + user2.name + ": ");
        boolean found = false;
        Friend temp2 = user2.friendList;
        while (temp2 != null) {
            if (friends1.contains(temp2.friendId)) {
                System.out.print(temp2.friendId + " ");
                found = true;
            }
            temp2 = temp2.next;
        }

        if (!found) {
            System.out.println("No mutual friends.");
        } else {
            System.out.println();
        }
    }

    // Display all friends of a specific user
    public void displayUserFriends(int userId) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        user.displayFriends();
    }

    // Count the number of friends for a user
    public void countFriendsForUser(int userId) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println(user.name + " has " + user.countFriends() + " friends.");
    }

    public static void main(String[] args) {
        SocialMedia network = new SocialMedia();

        // Adding users
        network.addUser(1, "Manan", 22);
        network.addUser(2, "Nikhil", 24);
        network.addUser(3, "Sagar", 21);
        network.addUser(4, "Dhruv", 23);

        // Adding friendships
        network.addFriendConnection(1, 2);
        network.addFriendConnection(1, 3);
        network.addFriendConnection(2, 3);
        network.addFriendConnection(2, 4);

        // Display all friends
        network.displayUserFriends(1);
        network.displayUserFriends(2);

        // Find mutual friends
        network.findMutualFriends(1, 2);

        // Count friends
        network.countFriendsForUser(1);
        network.countFriendsForUser(2);

        // Remove a friendship
        network.removeFriendConnection(1, 3);
        network.displayUserFriends(1);
    }
}

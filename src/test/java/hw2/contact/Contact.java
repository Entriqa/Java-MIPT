package hw2.contact;


record Contact(String username, String email) implements Comparable<Contact> {
    public static final String UNKNOWN_EMAIL = "unknown";

    Contact {
        if (username.isBlank()) {
            throw new InvalidContactFieldException("username");
        }
        if (email != null && !email.endsWith("@gmail.com")) {
            throw new InvalidContactFieldException("email");
        }
        if (email == null) {
            email = UNKNOWN_EMAIL;
        }
    }

    Contact(String username) {
        this(username, null);
    }


    public int compareTo(Contact o) {
        return this.username.length() - o.username.length();
    }
}
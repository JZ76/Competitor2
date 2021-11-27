public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    public Name (String fullName) {
        try {
            int space1 = fullName.indexOf(' ');
            firstName = fullName.substring(0, space1).replace(" ", "");

            int space2 = fullName.lastIndexOf(' ');
            if (space1 == space2) {
                middleName = "";
            }else {
                middleName = fullName.substring(space1 + 1, space2).replace(" ", "");
            }

            lastName = fullName.substring(space2 + 1).replace(" ", "");
        } catch (IndexOutOfBoundsException name){
            System.out.println("There is no SPACE between names: " + fullName);
        }
    }

    public String getFullName() {
        try {
            String result = firstName + " ";
            if (!middleName.equals("")) {
                result += middleName + " ";
            }
            result += lastName;
            return result;
        }catch (NullPointerException name){
            return "WRONG NAME FORMAT";
        }
    }

    public String getInitials(){
        try {
            if (!middleName.equals("")) {
                String initial = firstName.substring(0, 1) + middleName.substring(0, 1) + lastName.substring(0, 1);
                return initial.toUpperCase();
            }else {
                String initial = firstName.substring(0, 1) + lastName.substring(0, 1);
                return initial.toUpperCase();
            }
        }catch (NullPointerException name){
            return "WRONG NAME FORMAT";
        }
    }

    //to generate a format that Zhang, Jiancheng
    public String getLCF(){
        return lastName + ", " + firstName;
    }

    //get the first letter of lastname and be used for sort list by this
    public String getLastName() {
        return lastName.substring(0, 1);
    }
}


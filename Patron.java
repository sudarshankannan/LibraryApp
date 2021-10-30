class Patron{
    String fname;
    String lname;
    int userID;
    int pin;
    int numBooks;
    float fines;
    //constructors
    Patron(){
        this.fname = null;
        this.lname = null;
        this.userID = -1;
        this.pin = -1;
        this.numBooks = 0;
        this.fines = 0;
    }
    //getters
    String getFirstName(){
        return this.fname;
    }
    String getLastName(){
        return this.lname;
    }
    int getUserID(){
        return this.userID;
    }
    int getPIN(){
        return this.pin;
    }
    int getNumBooks(){
        return this.numBooks;
    }
    float getFines(){
        return this.fines;
    }
    //setters
    void setFirstName(String fname){
        this.fname = fname;
    } 
    void setLastName(String lname){
        this.lname = lname;
    }
    void setUserID(int userID){
        this.userID = userID;
    }
    void setPIN(int pin){
        this.pin = pin;
    }
    void setNumBooks(int numBooks){
        this.numBooks = numBooks;
    }
    void setFines(float fines){
        this.fines = fines;
    }
}
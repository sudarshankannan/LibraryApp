class Librarian{
    int userID;
    int pin;
    //constructors
    Librarian(){
        this.userID = -1;
        this.pin = -1;
    }
    //getters
    int getUserID(){
        return this.userID;
    }
    int getPIN(){
        return this.pin;
    }
    //setters
    void setFirstName(int userID){
        this.userID = userID;
    }
    void setPIN(int pin){
        this.pin = pin;
    }
}
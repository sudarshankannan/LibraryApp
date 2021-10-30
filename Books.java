class Books{
    String title;
    String author;
    int numBooks;
    //constructors
    Books(){
        String title = null;
        String author = null;
        int numBooks = 3;
    }
    //getters
    String getTitle(){
        return this.title;
    }
    String getAuthor(){
        return this.author;
    }
    int getNumBooks(){
        return this.numBooks;
    }
    //setters
    void setTitle(String title){
        this.title = title;
    }
    void setAuthor(String author){
        this.author = author;
    }
    void setNumBooks(int numBooks){
        this.numBooks = numBooks;
    }
}
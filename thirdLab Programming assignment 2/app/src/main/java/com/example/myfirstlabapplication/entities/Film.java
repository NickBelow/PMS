package com.example.myfirstlabapplication.entities;



public class Film {
    public String Title;
    public int Year;
    public String imdbID;
    public String Type;
    public String Poster;


    public String getType() {
        return Type;
    }

    public int getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getPoster() {
        return Poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setposter(String poster) {
        this.Poster = poster;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public void setYear(int year) {
        this.Year = Year;
    }


    Film(){}

    @Override
    public String toString() {
        return "Film{" +
                "Title='" + Title + '\'' +
                ", Year=" + Year +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", Poster='" + Poster + '\'' +
                '}';
    }
}
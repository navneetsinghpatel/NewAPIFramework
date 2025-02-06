package model;


import lombok.Data;
import java.util.ArrayList;

@Data
public class UsersResponse{
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<Data> data;
    public Support support;

    @lombok.Data
    public static class Data{
        public int id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;
    }

    @lombok.Data
    public static class Support{
        public String url;
        public String text;
    }
}




package com.example.anyDTO;

import java.util.ArrayList;

public class ItemDTO {

    private String numid;
    private String korid;
    private String description;
    private String plaintext;
    private int base;
    private int total;
    private int sell;
    private int depth;
    private String tag = "기타";
    private ArrayList<MainDTO> main = new ArrayList<>();
    private ArrayList<SubDTO> sub = new ArrayList<>();

    public String getNumid() {
        return numid;
    }

    public void setNumid(String numid) {
        this.numid = numid;
    }

    public String getKorid() {
        return korid;
    }

    public void setKorid(String korid) {
        this.korid = korid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = this.tag + ", " + tag;
    }

    public void finishTag(String tag) {
        this.tag = tag;
    }

    public ArrayList<MainDTO> getMain() {
        return main;
    }

    public void setMain(MainDTO main) {
        this.main.add(main);
    }

    public ArrayList<SubDTO> getSub() {
        return sub;
    }

    public void setSub(SubDTO sub) {
        this.sub.add(sub);
    }


    public class MainDTO {
        private String numid;
        private String main;

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

    public class SubDTO {
        private String numid;
        private String sub;

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }
    }

    public class TagDTO {
        private String numid;
        private String tag;

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}

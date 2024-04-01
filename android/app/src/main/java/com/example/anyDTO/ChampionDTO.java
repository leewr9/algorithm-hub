package com.example.anyDTO;

import java.util.ArrayList;
import java.util.List;

public class ChampionDTO {
    private int numid;
    private String engid;
    private String korid;
    private String title;
    private List<SkillDTO> skill = new ArrayList<>();
    private List<SkinDTO> skin = new ArrayList<>();

    public int getNumid() {
        return numid;
    }

    public void setNumid(int numid) {
        this.numid = numid;
    }

    public String getEngid() {
        return engid;
    }

    public void setEngid(String engid) {
        this.engid = engid;
    }

    public String getKorid() {
        return korid;
    }

    public void setKorid(String korid) {
        this.korid = korid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SkillDTO> getSkill() {
        return skill;
    }

    public void setSkill(SkillDTO skill) {
        this.skill.add(skill);
    }

    public List<SkinDTO> getSkin() {
        return skin;
    }

    public void setSkin(SkinDTO skin) {
        this.skin.add(skin);
    }

    public class SkillDTO {
        private int no;
        private int numid;
        private String engid;
        private String korid;
        private String description;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getNumid() {
            return numid;
        }

        public void setNumid(int numid) {
            this.numid = numid;
        }

        public String getEngid() {
            return engid;
        }

        public void setEngid(String engid) {
            this.engid = engid;
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
    }

    public class SkinDTO {
        private int numid;
        private int num;
        private String korid;

        public int getNumid() {
            return numid;
        }

        public void setNumid(int numid) {
            this.numid = numid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getKorid() {
            return korid;
        }

        public void setKorid(String korid) {
            this.korid = korid;
        }
    }
}

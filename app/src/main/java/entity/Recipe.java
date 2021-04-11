package entity;

public class Recipe {

    int id;
    String title;
    String cat;
    String author;
    String img;
    int imgStarId;
    int difficulty;
    String ingredients;
    String preparations;
    int nb;
    String temps;


    @Override
    public String toString(){
        return "Repice{" +
                "Cat='" + cat + '\'' +
                ", titles='" + title + '\'' +
                ", imgId=" + img +
                ", author='" + author + '\'' +
                '}';

    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }



    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparations() {
        return preparations;
    }

    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getImgStarId() {
        return imgStarId;
    }

    public void setImgStarId(int imgStarId) {
        this.imgStarId = imgStarId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTemps() {
        String retour;
        String[] split = temps.split(":");
        if(split[0] == "00"){
            retour = split[1];
        }else{
            retour = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]) + " mn";
        }
        return retour;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }
}

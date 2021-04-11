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

    @Override
    public String toString(){
        return "Repice{" +
                "Cat='" + cat + '\'' +
                ", titles='" + title + '\'' +
                ", imgId=" + img +
                ", author='" + author + '\'' +
                '}';

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
}

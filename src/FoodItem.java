public class FoodItem implements Comparable<FoodItem>{
    private int id, calories, protein, sugar;
    private String nameOfFood;

    public FoodItem(int id, int calories, int protein, int sugar, String nameOfFood) {
        setId(id);
        setCalories(calories);
        setProtein(protein);
        setSugar(sugar);
        setNameOfFood(nameOfFood);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0)
            this.id = id;
        else
            throw new IllegalArgumentException("Id must be greater than 0");
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        if(calories >= 0 && calories <= 5000)
            this.calories = calories;
        else
            throw new IllegalArgumentException("Calories must be in the range 0-5000");
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        if(protein >= 0 && protein <= 200)
            this.protein = protein;
        else
            throw new IllegalArgumentException("Protein must be in the range of 0-200");
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        if(sugar >= 0 && sugar <= 200)
            this.sugar = sugar;
        else
            throw new IllegalArgumentException("Sugar must be in the range of 0-200");
    }

    public String getNameOfFood() {
        return nameOfFood;
    }

    public void setNameOfFood(String nameOfFood) {
        this.nameOfFood = nameOfFood;
    }

    public String toString() {
        return nameOfFood;
    }

    @Override
    public int compareTo(FoodItem otherFood) {
        return this.nameOfFood.toLowerCase().compareTo(otherFood.nameOfFood.toLowerCase());
    }
}


package wasif.fyp.smartrestaurant;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;


@Entity
public class CanceledOrderProjectBox {
    @Id
    long Id;

    private String name;
    private String category_id;
    private String item_type;
    private String item_price;

    public CanceledOrderProjectBox() {
    }

    public CanceledOrderProjectBox(long id, String name, String category_id, String item_type, String item_price) {
        Id = id;
        this.name = name;
        this.category_id = category_id;
        this.item_type = item_type;
        this.item_price = item_price;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
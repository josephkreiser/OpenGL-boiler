package Entity;

public interface IEntity {
    public double get_x_pos();
    public double get_y_pos();
    public double get_x_speed();
    public double get_y_speed();

    public void set_x_pos(double x_pos);
    public void set_y_pos(double y_pos);
    public void set_x_speed(double x_speed);
    public void set_y_speed(double y_speed);

    public boolean is_entity_on_ground();

    public void init();

    public void tick(double delta);

    public void draw();
}

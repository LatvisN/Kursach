import com.example.kursach.*;
import org.junit.Test;

public class test{
    @Test
    public void testViews()  {
        new ControllerUserMenu();
        new ControllerEmpMenu();
        new ControllerAdminMenu();
    }
    @Test
    public void testAdd()  {
        new ControllerServiceAdd();
    }
    @Test
    public void testDelete()  {
        new ControllerVisitDelete();
    }
}
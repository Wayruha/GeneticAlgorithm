import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.wayruha.Individual;
import com.wayruha.fitness.Point;
import com.wayruha.fitness.TestSolution;

public class RunSolutionTest {

    private boolean[][] map;
    private Point startPoint;
    private TestSolution instance;
    @Before
    public void init(){
        startPoint=new Point(6,5);
        map = new boolean[][]{
                {true, true, false, true, false, false, false},                   //1101000
                {false, false, false, false, false, false, false,},               //0000000
                {false, false, true, false, false, true, false},                  //0010010
                {true, false, true, false, true, false, true},                    //1010101
                {true, false, false, false, false, false, true},                  //1000001
                {false, false, true, true, true, true, true},                     //0011111
                {false, false, false, false, false, false, false}                 //0000000

        };
        instance=new TestSolution(new Individual(),map,startPoint);

    }

    @Test
    public void isTrashRelativeToPosituationNumberion(){
        Assert.assertEquals(true,instance.isTrashRelativeToPosition(map,startPoint,0,0));
        Assert.assertEquals(true,instance.isTrashRelativeToPosition(map,startPoint,-1,0));
        Assert.assertEquals(true,instance.isTrashRelativeToPosition(map,startPoint,0,-1));
        Assert.assertEquals(false,instance.isTrashRelativeToPosition(map,startPoint,1,0));
        Assert.assertEquals(false,instance.isTrashRelativeToPosition(map,startPoint,0,1));

    }

    @Test
    public void getsituationNumberuationNumber() throws Exception {
        Assert.assertEquals(7,instance.getSituationNumber(map,startPoint));
        Assert.assertEquals(9,instance.getSituationNumber(map,new Point(0,0)));
        Assert.assertEquals(3,instance.getSituationNumber(map,new Point(1,0)));
        Assert.assertEquals(1,instance.getSituationNumber(map,new Point(3,0)));
        Assert.assertEquals(17,instance.getSituationNumber(map,new Point(2,2)));

    }

}
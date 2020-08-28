import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.MajorDao;
import com.courseDesign.dao.StudentDao;
import com.courseDesign.dao.University_Enroll_StudentDao;
import com.courseDesign.javabean.major;
import com.courseDesign.javabean.volunteer;

import java.util.ArrayList;

public class testdatabase {
    public static void main(String[] args) {
        StudentDao studentDao=new StudentDao();
        MajorDao majorDao=new MajorDao();

        ArrayList<volunteer> volunteers =studentDao.searchVolunteer(1,1);
        System.out.println(volunteers.size());
        for (int m = 0; m < volunteers.size(); m++) {
            major major=majorDao.searchMajor(volunteers.get(m).getClassid());
            String s=majorDao.searchClassname(volunteers.get(m).getClassid());
            System.out.println(major.getClassId());
            System.out.println(major.getNclass());
            System.out.println("***");
            System.out.println(s);
            System.out.println(volunteers.get(m).getType());
        }
    }
}

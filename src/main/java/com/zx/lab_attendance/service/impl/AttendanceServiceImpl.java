package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.*;
import com.zx.lab_attendance.entity.*;
import com.zx.lab_attendance.enums.AttendanceEnum;
import com.zx.lab_attendance.service.AttendanceService;
import com.zx.lab_attendance.utils.DateConversionWeek;
import com.zx.lab_attendance.vo.CourseAttendance;
import com.zx.lab_attendance.vo.PersonalAttendance;
import com.zx.lab_attendance.vo.StudentAttendance;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/11 12:37
 * @Description
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private LabusingMapper labusingMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseandstuMapper courseandstuMapper;
    @Autowired
    private CollectiveMapper collectiveMapper;


    /**
     * @author: zx
     * @paramater: attendance
     * @process: 获取attendance插入数据库
     * @describe: 添加考勤记录
     */
    @Override
    public int insertAttendance(Attendance attendance) {
        return attendanceMapper.insert(attendance);
    }

    /**
     * @author: zx
     * @paramater: attendance
     * @process: 传入参数对原来参数进行修改
     * @describe: 修改考勤记录
     */
    @Override
    public int updateAttendance(Attendance attendance) {
        return attendanceMapper.updateByPrimaryKey(attendance);
    }

    /**
     * @author: zx
     * @paramater: attenance
     * @process: 输入相关的参数，进行查询
     * @describe: 查询考勤数据
     */
    @Override
    public List<Attendance> selectAttendance(Attendance attendance) {
        return attendanceMapper.selectByConditions(attendance);
    }

    /**
     * @author: zx
     * @paramater: attendance_date考勤时间，班级id,学号/工号，课程编号
     * @process: 查出班级的多少，用班级的id，查找users表的人数，用userid查找考勤表的
     * 考勤情况并添加时间条件进行筛选，若添加学号，则查找对应的表格获取用户id，根据用户id在attendance表进行筛选。
     * 若添加课程编号，则在labusing表上获取id，在attendance进行筛选。
     * @describe: 根据班级查询对应考勤信息
     */
    @Override
    public List<Map<String,Map<String,List<PersonalAttendance>>>> selectByTimeAndCollective(String collectiveid,String starttime,String endtime){
        List<Users> usersList = usersMapper.selectByCourseId(collectiveid);
        List<PersonalAttendance> personalAttendanceList = new ArrayList<>();
        List<Map<String,List<PersonalAttendance>>> userAttendance = new ArrayList<>();
        List<Map<String,Map<String,List<PersonalAttendance>>>> newUserAttendance = new ArrayList<>();

        for (Users users : usersList){
            List<Attendance> attendanceList = attendanceMapper.selectByStudentID(users.getUserId(),starttime,endtime);
            HashMap<String,Map<String,List<PersonalAttendance>>> userMap = new HashMap<>();
            HashMap<String,List<PersonalAttendance>> courseMap = new HashMap<>();

                for (Attendance attendance : attendanceList) {
                    PersonalAttendance personalAttendance = new PersonalAttendance();
                    personalAttendance.setAttendanceid(attendance.getAttendanceId());
                    personalAttendance.setAttendancetime(attendance.getAttendanceDate());
                    if (attendance.getAttendanceRecord().equals(AttendanceEnum.NORMAL.type)) {
                        personalAttendance.setAttendanceState(AttendanceEnum.NORMAL.type);
                    } else if (attendance.getAttendanceRecord() == 2) {
                        personalAttendance.setAttendanceState(AttendanceEnum.LATE.type);
                    } else if (attendance.getAttendanceRecord() == 3) {
                        personalAttendance.setAttendanceState(AttendanceEnum.SICKLEAVE.type);
                    } else if (attendance.getAttendanceRecord() == 4) {
                        personalAttendance.setAttendanceState(AttendanceEnum.AFFAIRLEAVE.type);
                    } else if (attendance.getAttendanceRecord() == 5) {
                        personalAttendance.setAttendanceState(AttendanceEnum.ABSENTEEISM.type);
                    }
                    personalAttendance.setName(users.getUsername());
                    personalAttendance.setStudentNum(users.getUserNumber());
                    personalAttendanceList.add(personalAttendance);
                    Labusing labusing = labusingMapper.selectByPrimaryKey(attendance.getLabusingId());
                    Course course = courseMapper.selectByCourseCode(labusing.getCourseId());
                    personalAttendance.setCourseList(course);
                    if (courseMap.containsKey(course.getCourseCode()+course.getCourseName())){
                        List<PersonalAttendance> personalAttendancesList = courseMap.get(course.getCourseCode()+course.getCourseName());
                        personalAttendancesList.add(personalAttendance);
                    } else {
                        courseMap.put(course.getCourseCode()+course.getCourseName(),new ArrayList<>());
                        List<PersonalAttendance> personalAttendancesList = courseMap.get(course.getCourseCode()+course.getCourseName());
                        personalAttendancesList.add(personalAttendance);
                    }
                }
            userMap.put(users.getUserNumber()+users.getUsername(),courseMap);
            newUserAttendance.add(userMap);
        }
        return newUserAttendance;
    }

    /**
     * @author: zx
     * @paramater: 学生学号，时间段开始时间、结束时间
     * @process: 通过输入用户学号，查询用户表id，并且用userid查询attendance表的数据。
     * @describe: 通过输入学号查询用户考勤数据
     */
    @Override
    public  List<PersonalAttendance> selectByUserNum(String userNum, String starttime, String endtime) {
        Users users = usersMapper.selectUserByUserNum(userNum);
        if (StringUtils.isEmpty(users)) {
            return null;
        } else {
//            List<Map<String,List<PersonalAttendance>>> HashPersonal = new ArrayList<>();
            List<PersonalAttendance> personalAttendanceList = new ArrayList<>();
            List<Attendance> attendanceList = attendanceMapper.selectByStudentID(users.getUserId(), starttime, endtime);
//            Map<String,List<PersonalAttendance>> courseAttendance = new HashMap<>();
            for (Attendance attendance : attendanceList) {
                PersonalAttendance personalAttendance = new PersonalAttendance();
                personalAttendance.setAttendanceid(attendance.getAttendanceId());
                personalAttendance.setMajorName(users.getMajor().getMajorName());
                personalAttendance.setAttendancetime(attendance.getAttendanceDate());
                if (attendance.getAttendanceRecord().equals(AttendanceEnum.NORMAL.type)) {
                    personalAttendance.setAttendanceState(AttendanceEnum.NORMAL.type);
                } else if (attendance.getAttendanceRecord() == 2) {
                    personalAttendance.setAttendanceState(AttendanceEnum.LATE.type);
                } else if (attendance.getAttendanceRecord() == 3) {
                    personalAttendance.setAttendanceState(AttendanceEnum.SICKLEAVE.type);
                } else if (attendance.getAttendanceRecord() == 4) {
                    personalAttendance.setAttendanceState(AttendanceEnum.AFFAIRLEAVE.type);
                } else if (attendance.getAttendanceRecord() == 5) {
                    personalAttendance.setAttendanceState(AttendanceEnum.ABSENTEEISM.type);
                }
                personalAttendance.setName(users.getUsername());
                personalAttendance.setStudentNum(users.getUserNumber());
                personalAttendanceList.add(personalAttendance);
                Labusing labusing = labusingMapper.selectByPrimaryKey(attendance.getLabusingId());
                Course course = courseMapper.selectByCourseCode(labusing.getCourseId());
                personalAttendance.setCourseList(course);
//                if (courseAttendance.containsKey(course.getCourseCode()+course.getCourseName())){
//                    List<PersonalAttendance> p = courseAttendance.get(course.getCourseCode()+course.getCourseName());
//                    p.add(personalAttendance);
//                } else {
//                    courseAttendance.put(course.getCourseCode()+course.getCourseName(),new ArrayList<>());
//                    List<PersonalAttendance> p = courseAttendance.get(course.getCourseCode()+course.getCourseName());
//                    p.add(personalAttendance);
//                }

            }
//            HashPersonal.add(courseAttendance);
             return personalAttendanceList;
        }
    }

    /**
     * @author: zx
     * @paramater:
     * @process: 输入课程代码，在labusing的表内获取labusing，循环遍历labusingid查找attendance表的数据List<attendance>
     * @describe: 通过输入课程代码查询考勤信息
     */
    @Override
    public List<CourseAttendance> selectByCourseCode(String courseCode, String starttime, String endtime) {
        List<CourseAttendance> courseAttendances = new ArrayList<>();

        List<Labusing> labusings = labusingMapper.selectByCourseCodeDate(courseCode,starttime,endtime);
        for (Labusing labusing : labusings) {
            CourseAttendance courseAttendance = new CourseAttendance();
            List<Attendance> attendanceList = attendanceMapper.selectByCourseCode(labusing.getLabusingId(),starttime,endtime);
            System.out.println(attendanceList.size());
            int collectiveNum = attendanceList.size();
            int lateNum = 0;
            int sickNum = 0;
            int affirtNum = 0;
            int absenteeism = 0;
            for (Attendance attendance1 : attendanceList) {
                if (attendance1.getAttendanceRecord().equals(2)){
                    lateNum += 1;
                } else if (attendance1.getAttendanceRecord().equals(3)) {
                    sickNum += 1;
                } else if (attendance1.getAttendanceRecord().equals(4)) {
                    affirtNum += 1;
                } else if (attendance1.getAttendanceRecord().equals(5)) {
                    absenteeism += 1;
                }
            }
            courseAttendance.setCourseManNum(collectiveNum);
            courseAttendance.setLateNum(lateNum);
            courseAttendance.setSickNum(sickNum);
            courseAttendance.setAffairLeaveNum(affirtNum);
            courseAttendance.setAbsenteeism(absenteeism);
            courseAttendance.setCourseNum(labusing.getCourseId());
            Course course = courseMapper.selectByCourseCode(labusing.getCourseId());
            courseAttendance.setCourseName(course.getCourseName());
            courseAttendance.setCourseDate(labusing.getLabusingDate());
            Courseandstu courseandstu = new Courseandstu();
            if (attendanceList.size() > 0) {
                courseandstu = courseandstuMapper.selectByStudentId(attendanceList.get(0).getStudentId(), labusing.getCourseId());
            }
            courseAttendance.setCourseClass(courseandstu.getCourseNumber());
            courseAttendances.add(courseAttendance);
        }
        return courseAttendances;
    }

    /**
     * @author: zx
     * @paramater: nul;
     * @process:  获取两周的时间，查找考勤记录获取考勤数
     * @describe: 获取当前时间，并获取当前周与上一周的考勤情况
     */
    @Override
    public List<Double> selectOutputPercentage() throws ParseException {
        Date nowday = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowdayStr = sdf.format(nowday);
        List<String> lastWeek = new ArrayList<>();
        List<String> thisWeek = new ArrayList<>();
        int weekDay = DateConversionWeek.DateConversionWeek(nowdayStr);
        for (int i = 0;i < 7; i++) {
            Date date = new java.sql.Date(nowday.getTime());
            Date as = new Date(date.getTime()-(i+weekDay)*24*60*60*1000);
            String newtime = sdf.format(as);
            lastWeek.add(newtime);
        }
        for (int i = 0;i < weekDay;i++){
            Date date = new java.sql.Date(nowday.getTime());
            Date as = new Date(date.getTime()-i*24*60*60*1000);
            String newtime = sdf.format(as);
            thisWeek.add(newtime);
        }
        List<Attendance> allAttendance = null;
        List<Attendance> normalAttendance = null;
        List<Double> doubleList = new ArrayList<>();
        for (String time : lastWeek) {
            normalAttendance =  attendanceMapper.selectByDateAndStaus(time);
            allAttendance = attendanceMapper.selectByDate(time);
            if (normalAttendance.size() != 0 && allAttendance.size() != 0) {
                double percentage = (double) normalAttendance.size() / allAttendance.size();
                doubleList.add(percentage);
            } else if (normalAttendance.size() == 0){
                double percentage = 0;
                doubleList.add(percentage);
            }
            allAttendance.clear();
            normalAttendance.clear();
        }
        for (String time : thisWeek) {
            normalAttendance = attendanceMapper.selectByDateAndStaus(time);
            allAttendance = attendanceMapper.selectByDate(time);
            Map<String, Double> maplast = new HashedMap();
            if (normalAttendance.size() != 0 && allAttendance.size() != 0) {
                double percentage = (double) normalAttendance.size() / allAttendance.size();
                doubleList.add(percentage);
            } else if (normalAttendance.size() == 0){
                    double percentage = 0;
                doubleList.add(percentage);
            }
        }
        return doubleList;
    }

    /**
     * @author: zx
     * @paramater: null
     * @process: 查找考勤情况为确认的人数，并获取对应的userid，查出对应的用户是哪个系的。
     * @describe: 查看各个年级的缺勤数
     */
    @Override
    public List<Map> selectAbsenteeism(String starttime, String endtime) {
        List<Map> mapList = new ArrayList<>();
        List<Attendance> attendanceList = attendanceMapper.selectByStaus(starttime, endtime);
        Map<String,Integer> map = new HashedMap();
        for (Attendance attendance : attendanceList){
            Users u = usersMapper.selectByPrimaryKey(attendance.getStudentId());
            Collective collective = collectiveMapper.selectByPrimaryKey(u.getClassordepartment());
            if (map.containsKey(collective.getDepartment().getDepartmentDescribe())){
                int i = map.get(collective.getDepartment().getDepartmentDescribe());
                i += 1;
                map.put(collective.getDepartment().getDepartmentDescribe(),i);
            } else {
                map.put(collective.getDepartment().getDepartmentDescribe(),1);
            }
        }
        for (Map.Entry<String, Integer> vo : map.entrySet()){
            Map map1 = new HashMap();
            map1.put("name",vo.getKey());
            map1.put("value",vo.getValue());
            mapList.add(map1);
        }
        return mapList;
    }

    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 查询所有的课程的考勤记录
     */
    @Override
    public List<CourseAttendance> selectAllAttendanceCourse() {
        List<Course> courses = courseMapper.selectAll();
        List<CourseAttendance> courseAttendances = new ArrayList<>();
        for (Course course : courses) {
            List<Labusing> labusings = labusingMapper.selectByCourseCode(course.getCourseCode());
            for (Labusing labusing : labusings){
                CourseAttendance courseAttendance = new CourseAttendance();
                List<Attendance> attendances = attendanceMapper.selectByCourseCode(labusing.getLabusingId(),null,null);
                int collectiveNum = attendances.size();
                int lateNum = 0;
                int sickNum = 0;
                int affirtNum = 0;
                int absenteeism = 0;
                for (Attendance attendance : attendances){
                    if (attendance.getAttendanceRecord().equals(2)){
                        lateNum += 1;
                    } else if (attendance.getAttendanceRecord().equals(3)) {
                        sickNum += 1;
                    } else if (attendance.getAttendanceRecord().equals(4)) {
                        affirtNum += 1;
                    } else if (attendance.getAttendanceRecord().equals(5)) {
                        absenteeism += 1;
                    }
                }
                courseAttendance.setCourseManNum(collectiveNum);
                courseAttendance.setLateNum(lateNum);
                courseAttendance.setSickNum(sickNum);
                courseAttendance.setAffairLeaveNum(affirtNum);
                courseAttendance.setAbsenteeism(absenteeism);
                courseAttendance.setCourseNum(labusing.getCourseId());
                courseAttendance.setCourseName(course.getCourseName());
                courseAttendance.setCourseDate(labusing.getLabusingDate());
                courseAttendances.add(courseAttendance);
            }
        }
        Map<String,CourseAttendance> attendanceMap = new HashedMap();
        for (CourseAttendance courseAttendance : courseAttendances){
            if (attendanceMap.size() == 0){
                attendanceMap.put(courseAttendance.getCourseNum(),courseAttendance);
            } else {
                if (attendanceMap.containsKey(courseAttendance.getCourseNum())){
                    CourseAttendance courseAttendance1 = attendanceMap.get(courseAttendance.getCourseNum());
                    int lateNum = courseAttendance1.getLateNum() + courseAttendance.getLateNum();
                    int sickNum = courseAttendance1.getSickNum() + courseAttendance.getSickNum();
                    int afficeNum = courseAttendance1.getAffairLeaveNum() + courseAttendance.getAffairLeaveNum();
                    int absenNum = courseAttendance1.getAbsenteeism() + courseAttendance.getAbsenteeism();
                    courseAttendance1.setLateNum(lateNum);
                    courseAttendance1.setSickNum(sickNum);
                    courseAttendance1.setAffairLeaveNum(afficeNum);
                    courseAttendance1.setAbsenteeism(absenNum);
                    attendanceMap.put(courseAttendance.getCourseNum(),courseAttendance1);
                }else{
                    attendanceMap.put(courseAttendance.getCourseNum(),courseAttendance);
                }

            }
        }
        List<CourseAttendance> attendances = new ArrayList<>();
        for(CourseAttendance value : attendanceMap.values()){
            attendances.add(value);
        }
        return attendances;
    }


    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 查看所有学生的考勤统计
     */
    @Override
    public List<StudentAttendance> selectAllStuAttendance() {
        List<Users> users = usersMapper.selectByAllStudent();
        List<StudentAttendance> studentAttendances = new ArrayList<>();
        for (Users users1 : users){
            List<Attendance> attendanceList = attendanceMapper.selectByStudentID(users1.getUserId(),null,null);
            int lateNum = 0;
            int sickNum = 0;
            int affirtNum = 0;
            int absenteeism = 0;
            for (Attendance attendance : attendanceList) {
                if (attendance.getAttendanceRecord().equals(2)){
                    lateNum += 1;
                } else if (attendance.getAttendanceRecord().equals(3)) {
                    sickNum += 1;
                } else if (attendance.getAttendanceRecord().equals(4)) {
                    affirtNum += 1;
                } else if (attendance.getAttendanceRecord().equals(5)) {
                    absenteeism += 1;
                }
            }
            StudentAttendance studentAttendance = new StudentAttendance();
            studentAttendance.setUsername(users1.getUsername());
            studentAttendance.setUserNumber(users1.getUserNumber());
            studentAttendance.setUserId(users1.getUserId());
            studentAttendance.setAbsenteeism(absenteeism);
            studentAttendance.setAffairLeaveNum(affirtNum);
            studentAttendance.setLateNum(lateNum);
            studentAttendance.setSickNum(sickNum);
            studentAttendance.setDepartment(users1.getMajor().getMajorName());
            studentAttendances.add(studentAttendance);
        }
        return studentAttendances;
    }

    @Override
    public List<PersonalAttendance> selectStudentCollect(String courseCode, String userid, String starttime, String endtime) {
        Users users = usersMapper.selectByPrimaryKey(userid);
            List<PersonalAttendance> personalAttendanceList = new ArrayList<>();
            Course course = courseMapper.selectByCourseCode(courseCode);
            List<Labusing> labusings = labusingMapper.selectByCourseCode(course.getCourseCode());
            for (Labusing labusing : labusings){
                Attendance attendance = attendanceMapper.selectByStudentIDAndLab(labusing.getLabusingId(),users.getUserId(),starttime,endtime);
                if (!StringUtils.isEmpty(attendance)) {
                    PersonalAttendance personalAttendance = new PersonalAttendance();
                    personalAttendance.setAttendanceid(attendance.getAttendanceId());
                    personalAttendance.setMajorName(users.getMajor().getMajorName());
                    personalAttendance.setAttendancetime(attendance.getAttendanceDate());
                    if (attendance.getAttendanceRecord().equals(AttendanceEnum.NORMAL.type)) {
                        personalAttendance.setAttendanceState(AttendanceEnum.NORMAL.type);
                    } else if (attendance.getAttendanceRecord() == 2) {
                        personalAttendance.setAttendanceState(AttendanceEnum.LATE.type);
                    } else if (attendance.getAttendanceRecord() == 3) {
                        personalAttendance.setAttendanceState(AttendanceEnum.SICKLEAVE.type);
                    } else if (attendance.getAttendanceRecord() == 4) {
                        personalAttendance.setAttendanceState(AttendanceEnum.AFFAIRLEAVE.type);
                    } else if (attendance.getAttendanceRecord() == 5) {
                        personalAttendance.setAttendanceState(AttendanceEnum.ABSENTEEISM.type);
                    }
                    personalAttendance.setName(users.getUsername());
                    personalAttendance.setStudentNum(users.getUserNumber());
                    personalAttendance.setCourseList(course);
                    personalAttendanceList.add(personalAttendance);
                }

            }

            return personalAttendanceList;
    }

    @Override
    public List<CourseAttendance> selectTeacherCollect(String courseCode, String userId, String starttime, String endtime) {
        List<CourseAttendance> courseAttendances = new ArrayList<>();

        List<Labusing> labusings = labusingMapper.selectByCourseCodeDate(courseCode, starttime, endtime);
        for (Labusing labusing : labusings) {
            CourseAttendance courseAttendance = new CourseAttendance();
            List<Attendance> attendanceList = attendanceMapper.selectByTeacherIDAndLab(labusing.getLabusingId(), userId,starttime, endtime);
            System.out.println(attendanceList.size());
            int collectiveNum = attendanceList.size();
            int lateNum = 0;
            int sickNum = 0;
            int affirtNum = 0;
            int absenteeism = 0;
            for (Attendance attendance1 : attendanceList) {
                if (attendance1.getAttendanceRecord().equals(2)) {
                    lateNum += 1;
                } else if (attendance1.getAttendanceRecord().equals(3)) {
                    sickNum += 1;
                } else if (attendance1.getAttendanceRecord().equals(4)) {
                    affirtNum += 1;
                } else if (attendance1.getAttendanceRecord().equals(5)) {
                    absenteeism += 1;
                }
            }
            courseAttendance.setCourseManNum(collectiveNum);
            courseAttendance.setLateNum(lateNum);
            courseAttendance.setSickNum(sickNum);
            courseAttendance.setAffairLeaveNum(affirtNum);
            courseAttendance.setAbsenteeism(absenteeism);
            courseAttendance.setCourseNum(labusing.getCourseId());
            Course course = courseMapper.selectByCourseCode(labusing.getCourseId());
            courseAttendance.setCourseName(course.getCourseName());
            courseAttendance.setCourseDate(labusing.getLabusingDate());
            Courseandstu courseandstu = new Courseandstu();
            if (attendanceList.size() > 0) {
                courseandstu = courseandstuMapper.selectByStudentId(attendanceList.get(0).getStudentId(), labusing.getCourseId());
            }
            courseAttendance.setCourseClass(courseandstu.getCourseNumber());
            courseAttendances.add(courseAttendance);
        }
        return courseAttendances;
    }
}




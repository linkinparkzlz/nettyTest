package com.zou.protobuf;

public class ProtoBufTest {

    public static void main(String[] args) throws Exception {

        //将对象构造好，将其放进字节数组里；再从字节数据里将对象还原出来

        //构造对象
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("zou").setAge(22).setAddress("北京").build();

        //将其转换为字节数组
        byte[] studentToByteArray = student.toByteArray();

        //再将其转换为对象（反序列化出来）
        DataInfo.Student student1 = DataInfo.Student.parseFrom(studentToByteArray);

        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student.getAddress());

    }

}

/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Title: ProtoBuffSerialTest
 * @Description:
 * @Author zhujing
 * @Date 2019/6/10
 * @Version V1.0
 */
public class ProtoBuffSerialTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        UserProto.UserProtoDTO userProtoDTO = UserProto.UserProtoDTO.newBuilder().setName("steven").setAge(18).build();
        byte[] bytes = userProtoDTO.toByteArray();
        for (byte by : bytes){
            // 10 6 115 116 101 118 101 110 16 18
            // steven --> 对应ascii码分别是, s->115, t->116, e->101, v->118, e->101, n->110
            // 数字18 --> 就是数字18
            // protobuff存储格式: T(Tag)/L(Length)/V(Value)

            // Tag的计算公式: field_number(就是xxx.proto中设置的字段编号)<<3|wire_type
            // wire_type对照表请参照: ProtoBuff-Wire_Type对照表.png, 正数使用varint算法, 负数使用zigzag算法
            // 字段name对应的field_number是1, wire_type是2, 那也就是 1<<3|2=10
            // 字段age对应的field_number是2, wire_type是0, 那也就是 2<<3|0=16

            // 10 --> 字段name计算后的值, 也就是 1<<3|2=10
            // 6 --> 字段name的值对应的长度
            // 115 116 101 118 101 110 --> 分别对应name字段中的值steven的ascii码
            // 16 --> 字段age计算后的值, 也就是 2<<3|0=16
            // 18 --> 字段age的值18

            // 如果age=200, 10 6 115 116 101 118 101 110 16 -56 1
            // 在二进制中表示负数的方法, 高位设置为1, 并且是对应数字的二进制取反以后再计算补码表示(补码是反码+1)
            // 200的二进制位: 0000 0000 0000 0000 0000 0000 1100 1000
            // 第一步: 从字节末尾取7位, 并在最高位补1, 那也就是1100 1000
            // 第二步: 整体右移7位, 并在最高位补0, 那也就是000 0000 1
            // 第三步: 最终结果: 1100 1000 0000 0001

            // 第四步: 先处理 1100 1000
            // 补码: 1100 1000 -1 = 1100 0111
            // 反码: 1100 0111 = 0011 1000 = 56
            // 因为高位是1, 表示负数, 结果为-56

            // 第五步: 再处理0000 0001
            // 补码: 0000 0001 - 1 = 1111 1110
            // 反码: 1111 1110 = 0000 0001 = 1
            System.out.print(by + " ");
        }

        System.out.println();
        UserProto.UserProtoDTO userProtoDTO1 = UserProto.UserProtoDTO.parseFrom(bytes);
        System.out.println(userProtoDTO1);
    }
}

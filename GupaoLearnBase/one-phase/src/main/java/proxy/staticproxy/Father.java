/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.staticproxy;

import proxy.Person;

/**
 * @Title: Father
 * @Description:
 * @Author zhujing
 * @Date 2019/4/11
 * @Version V1.0
 */
public class Father {

    private Person person;

    public Father(Son son){
        this.person = son;
    }

    public void findLove(){
        System.out.println("父亲替儿子物色对象");
        person.findLove();
        System.out.println("双方父母同意, 确立关系！！！");
    }
}

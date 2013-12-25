package com.hermes.app.ladp;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

/**
 * LDAP员工信息
 * @author of546
 */
public class Employee {

    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    private String uid;         // 账号
    private String fullName;    // 全名
    private String firstName;   // 姓
    private String lastName;    // 名字
    private String displayName; // 中文姓名
    private String no;          // 工号
    private String email;       // 邮箱
    private String mobile;      // 手机
    private String password;    // 密码 sha256(password + uid)
    private String description; // 描述
    // 部门 department
    // 岗位 position

    private LdapTemplate ldapTemplate;

    /**
     * DN设置
     * @param uid
     * @return
     */
    protected Name buildDn(String uid) {
        DistinguishedName dn = new DistinguishedName();
        dn.add("ou", "employee");
        dn.add("uid", uid);
        return dn;
    }

    /**
     * 数据绑定对象
     */
    private class EmployeeAttributesMapper implements AttributesMapper {
        public Object mapFromAttributes(Attributes attrs) throws NamingException {
            Employee e = new Employee();
            e.setUid((String) attrs.get("uid").get());
            e.setFullName((String) attrs.get("cn").get());
            e.setFirstName((String) attrs.get("sn").get());
            e.setLastName((String) attrs.get("givenName").get());
            e.setDisplayName((String) attrs.get("displayName").get());
            e.setNo((String) attrs.get("employeeNumber").get());
            e.setEmail((String) attrs.get("mail").get());
            e.setMobile((String) attrs.get("telephoneNumber").get());
            e.setPassword(new String((byte[]) attrs.get("userPassword").get()));   // password type byte[]
            e.setDescription((String) attrs.get("description").get());
            return e;
        }
    }

    /**
     * 判断能否登录
     *
     * @param username 帐号
     * @param password 密码
     * @return true允许, false:禁止
     */
    public Result login(String username, String password) {
        try {
            Employee e = find(username);
            if (e != null && StringUtils.equals(makePassword(password, e.getUid()), e.getPassword())) {
                return new Result(true, e);
            }
        } catch (NameNotFoundException nfe) {
            logger.warn("帐号:{}登陆失败", new Object[]{username, nfe});
        }
        return new Result(false, null);
    }

    /**
     * 查找员工
     *
     * @param uid 帐号
     * @return Employee
     */
    public Employee find(String uid) throws NameNotFoundException {
        Name dn = buildDn(uid);
        return (Employee) ldapTemplate.lookup(dn, new EmployeeAttributesMapper());
    }

    /**
     * 修改密码
     *
     * 注意:修改新密码之前判断当前密码是否正确
     *
     * @param e uid, newPassword
     * @return 加密后新密码
     */
    public String editPassword(Employee e) {
        String pwdHex = makePassword(e.getPassword(), e.getUid());
        BasicAttribute userPasswordAttribute = new BasicAttribute("userPassword", pwdHex);
        ModificationItem replacedPassword = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                userPasswordAttribute);
        ModificationItem[] modificationItemArray = new ModificationItem[1];
        modificationItemArray[0] = replacedPassword;
        ldapTemplate.modifyAttributes(buildDn(e.getUid()), modificationItemArray);
        return pwdHex;
    }

    /**
     * 【内部类】结果返回对象
     * 登录时返回成功或失败，成功登录返回对象信息
     */
    public static class Result {

        private boolean ok;
        private Employee value;

        public Result(boolean ok, Employee value) {
            this.ok = ok;
            this.value = value;
        }

        public boolean isOk() {
            return ok;
        }

        public Employee getValue() {
            return value;
        }

    }

    /**
     * sha256加密
     *
     * @param password 明文密码
     * @param salt     盐值
     * @return sha256(password + salt)
     */
    private String makePassword(String password, String salt) {
        return DigestUtils.sha256Hex(password + salt);
    }

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("uid='").append(uid).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", no='").append(no).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

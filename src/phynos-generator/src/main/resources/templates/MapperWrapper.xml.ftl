<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${base_package}.dao.mapper.ext.${model}MapperWrapper">

    <select id="getByName" resultMap="${base_package}.dao.mapper.${model}Mapper.BaseResultMap">
        select * from sys_user where `username` = #{username}
    </select>

</mapper>
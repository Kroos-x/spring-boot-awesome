package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * 功能描述: ${table.comment!} 服务类
 *
 * @Author ${author}
 * @Date ${date}
 * @Version: 1.0.0
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>

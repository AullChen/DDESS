package scnu.cstn2023.DDESS.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("scnu.cstn2023.DDESS.DAO")
public class MyBatisConfig {
    // 该配置类将会自动扫描 Mapper 接口
}

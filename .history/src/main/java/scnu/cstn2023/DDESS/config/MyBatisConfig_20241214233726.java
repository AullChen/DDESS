package Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.dataexchange.Dao")
public class MyBatisConfig {
    // 该配置类将会自动扫描 Mapper 接口
}

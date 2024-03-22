package vn.phat.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
@Configuration
public class CommonConfigBean {

    private static final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";
    private static final String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Bean
    ObjectMapper objectMapper(){
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT);
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatter = new SimpleDateFormat(LOCAL_DATE_TIME_FORMAT);
       return new Jackson2ObjectMapperBuilder()
               .serializers(new LocalDateSerializer(localDateFormatter), new LocalDateTimeSerializer(localDateTimeFormatter))
               .deserializers(new LocalDateDeserializer(localDateFormatter), new LocalDateTimeDeserializer(localDateTimeFormatter))
               .modules(new JavaTimeModule())
               .dateFormat(dateFormatter)
               .serializationInclusion(JsonInclude.Include.NON_NULL)
               .build();
    }

}

package com.cdwintech.app;

import top.bettercode.lang.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author Peter Wu
 */
public class BootTest {

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Test
  void sort() throws IOException {
    Map map = new ObjectMapper().readValue(new File(
            "/data/repositories/bettercode/wintruelife/template/.idea/httpRequests/2021-06-03T100104.200.json"),
        Map.class);
    Map timeline = (Map) map.get("timeline");
    List<Map> events = (List<Map>) timeline.get("events");
    events.sort(Comparator.comparing(o -> Duration.parse(o.get("duration").toString())));
    System.err.println(StringUtil.valueOf(events, true));
  }


}

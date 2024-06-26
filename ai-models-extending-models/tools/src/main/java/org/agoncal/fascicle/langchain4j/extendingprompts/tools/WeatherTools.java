package org.agoncal.fascicle.langchain4j.extendingprompts.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

class WeatherTools {

  @Tool("Returns the weather forecast for a given city")
  String getWeather(@P("The city for which the weather forecast for tomorrow should be returned") String city) {
    return "The weather in " + city + " is 25°C";
  }
}

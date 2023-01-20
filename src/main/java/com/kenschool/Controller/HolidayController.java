//package com.kenschool.Controller;
//
//import com.kenschool.Model_POJOs.POJOHoliday;
//import com.kenschool.Services.HolidayServices;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@Slf4j
//public class HolidayController {
//    @Autowired
//    private HolidayServices holidayServices;
//    // for the time being we are putting this in comment
////public ModelAndView ViewHolidays(@RequestParam(name = "festival",required = false) boolean Festival,
////                                     @RequestParam(name = "federal",required = fa lse) boolean Federal,Model model)
//    @RequestMapping("/holidays/{display}")
//    //  Using @PathVariable annot to get the path params
//    public ModelAndView ViewHolidays(@PathVariable(name = "display") String festive_name, Model model){
//
//        // As we have given the @PathVariable we need the business logic as
//        if (null!=festive_name&&festive_name.equals("all")){
//            model.addAttribute("boolfest",true);
//            model.addAttribute("boolfed",true);
//        }else if (null!=festive_name&&festive_name.equals("federal")){
//            model.addAttribute("boolfed",true);
//
//        }else if (null!=festive_name&&festive_name.equals("festival")){
//            model.addAttribute("boolfest",true);
//        }
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("holidays.html");
////        List<POJOHoliday> holidays = Arrays.asList(
////                new POJOHoliday(" Jan 1 ","New Year's Day", POJOHoliday.Type.FESTIVAL),
////                new POJOHoliday(" Oct 31 ","Halloween", POJOHoliday.Type.FESTIVAL),
////                new POJOHoliday(" Nov 24 ","Thanksgiving Day", POJOHoliday.Type.FESTIVAL),
////                new POJOHoliday(" Dec 25 ","Christmas", POJOHoliday.Type.FESTIVAL),
////                new POJOHoliday(" Jan 17 ","Martin Luther King Day", POJOHoliday.Type.FEDERAL),
////                new POJOHoliday(" July 4 ","Independence Day", POJOHoliday.Type.FEDERAL),
////                new POJOHoliday(" Sep 5 ","Labor Day", POJOHoliday.Type.FEDERAL),
////                new POJOHoliday(" Nov 11 ","Veterans Day", POJOHoliday.Type.FEDERAL)
////        );
//        List<POJOHoliday> holidays =holidayServices.accessTheHolidays();
////        System.out.println(holidays.get(0).toString());// Since we are using @Tostring in POJOHoliday corresponding toString is generated
//        // This is the another logic
//        POJOHoliday.Type[] types = POJOHoliday.Type.values(); //Getting the Values of the Enum Type
////        for (POJOHoliday.Type type:types) {
////            List<POJOHoliday> list = new ArrayList<>(); // Creating new new arrayList for each looping
////            for (POJOHoliday holi:holidays) {
////
////                if (type.equals(holi.getType())){
////                    list.add(holi);
////                }
////            }
////            System.out.println(list);
////            model.addAttribute(type.name(),list); // type.toString() can also be used
////        }
//
////=================================================================================================================
//        // We can simplify the code using the Stream API in java 8 the below code
////        POJOHoliday.Type[] types = POJOHoliday.Type.values();
//        for (POJOHoliday.Type type : types) {
//            model.addAttribute(type.toString(),
//                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
//        }
//        // Here i used .map(x -> {x.setDay("21 Jan");
//        //                        return x;
//        //                    }) inside the stream after filter and setted a value for the day field after removing final in POJO
//                               // Now all day become 21 Jan
////===============================================================================================================
//        // This is my way of Logic to reflect the Holiday data inside the web page
////        List<POJOHoliday> FEDERAL = new ArrayList<>();
////        List<POJOHoliday> FESTIVAL = new ArrayList<>();
////
////        for(POJOHoliday hol:holidays) {
//////            System.out.println("Inside For");
////            if (hol.getType().toString()=="FEDERAL"){
//////                log.info(hol.getReason());
////                FEDERAL.add(hol);
////            }else if(hol.getType().toString()=="FESTIVAL"){
//////                log.info(hol.getReason());
////                FESTIVAL.add(hol);
////
////            }
////        }
//////        log.info(FESTIVAL.get(0).getReason());
////
////        model.addAttribute("FEDERAL",FEDERAL);
////        model.addAttribute("FESTIVAL",FESTIVAL);
////        for (POJOHoliday federal:FEDERAL) {
////            System.out.println(federal.getDay()+"\t"+federal.getType()+"\t"+federal.getReason()+"\n");
////        }
//
//
//        return modelAndView;
//    }
//}

package com.kenschool.Controller;

import com.kenschool.Model_POJOs.NewPoJoHolidayEntity;
import com.kenschool.Repository.NewHolidayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@Slf4j
public class NewHolidayController {
    @Autowired
    private NewHolidayRepository newHolidayRepository;

    @RequestMapping("/holidays/{display}")
    public ModelAndView ViewHolidays(@PathVariable(name = "display") String festive_name, Model model) {

        if (null != festive_name && festive_name.equals("all")) {
            model.addAttribute("boolfest", true);
            model.addAttribute("boolfed", true);
        } else if (null != festive_name && festive_name.equals("federal")) {
            model.addAttribute("boolfed", true);

        } else if (null != festive_name && festive_name.equals("festival")) {
            model.addAttribute("boolfest", true);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("holidays.html");

        Iterable<NewPoJoHolidayEntity> holidaysIterator = newHolidayRepository.findAll();
        List<NewPoJoHolidayEntity> holidays = StreamSupport.stream(holidaysIterator.spliterator(), false)
                .collect(Collectors.toList());
        // Or just cast the receiving iterator to NewPoJoHoliday
//        List<NewPoJoHolidayEntity> holidays = (List<NewPoJoHolidayEntity>) newHolidayRepository.findAll();

        NewPoJoHolidayEntity.Type[] types = NewPoJoHolidayEntity.Type.values(); //Getting the Values of the Enum Type

        for (NewPoJoHolidayEntity.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }

        return modelAndView;
    }
}

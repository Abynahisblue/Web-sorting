package com.example.webapp.controller;



import com.example.webapp.model.SortResponse;
import com.example.webapp.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
public class SortController {
    @Autowired
    private SortService sortService;

    @RequestMapping("api/sort")
    public String test() {
        return "sort";
    }

    @PostMapping("api/sort-array")
    public String sortArray(@RequestParam("array") String arrayString,
                            @RequestParam("algorithm") String algorithm,
                            Model model) {
        System.out.println("ghgfdsjkakjfhhvgrd svjhczhgkdsf");
        int[] array = Arrays.stream(arrayString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] sortedArray;

        switch (algorithm) {
            case "heap":
                sortedArray = sortService.heapSort(array);
                break;
            case "quick":
                sortedArray = sortService.quickSort(array);
                break;
            default:
                sortedArray = sortService.quickSort(array);
                break;
        }

        SortResponse response = new SortResponse(sortedArray);
        addHateoasLinks(response, arrayString, algorithm);
        model.addAttribute("response", response);

        return "sortedList";
    }

    private void addHateoasLinks(SortResponse response, String arrayString, String algorithm) {
        Link selfLink = linkTo(methodOn(SortController.class).sortArray(arrayString, algorithm, null)).withSelfRel();
        Link heapSortLink = linkTo(methodOn(SortController.class).sortArray(arrayString, "heap", null)).withRel("heap-sort");
        Link quickSortLink = linkTo(methodOn(SortController.class).sortArray(arrayString, "quick", null)).withRel("quick-sort");

        response.add(selfLink);
        response.add(heapSortLink);
        response.add(quickSortLink);
    }



//    @PostMapping("/quick")
//    public SortResponse quickSort(@RequestBody int[] array) {
//        int[] sortedArray = sortService.quickSort(array);
//        SortResponse response = new SortResponse(sortedArray);
//        Link selfLink = linkTo(methodOn(SortController.class).quickSort(array)).withSelfRel();
//        Link heapSortLink = linkTo(methodOn(SortController.class).heapSort(array)).withRel("heap-sort");
//        response.add(selfLink);
//        response.add(heapSortLink);
//        return response;
//    }


}

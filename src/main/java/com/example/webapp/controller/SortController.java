package com.example.webapp.controller;




import com.example.webapp.exception.InvalidAlgorithmException;
import com.example.webapp.exception.InvalidArrayFormatException;
import com.example.webapp.model.SortResponse;
import com.example.webapp.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
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
        int[] array;
        try {
            array = Arrays.stream(arrayString.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new InvalidArrayFormatException("Invalid array format. Please provide a comma-separated list of integers.");
        }

        int[] sortedArray;
        switch (algorithm.toLowerCase()) {
            case "heap":
                sortedArray = sortService.heapSort(array);
                break;
            case "radix":
                sortedArray = sortService.radixSort(array);
                break;
            case "bucket":
                sortedArray = sortService.bucketSort(array);
                break;
            case "merge":
                sortedArray = sortService.mergeSort(array);
                break;
            case "quick":
                sortedArray = sortService.quickSort(array);
                break;
            default:
                throw new InvalidAlgorithmException("Invalid sorting algorithm. Supported algorithms are: heap, radix, bucket, merge, quick.");
        }

        SortResponse response = new SortResponse(sortedArray);
       // addHateoasLinks(response, arrayString, algorithm);
        model.addAttribute("response", sortedArray);

        return "sortedList";
    }

    private void addHateoasLinks(SortResponse response, String arrayString, String algorithm) {
        Link selfLink = linkTo(methodOn(SortController.class).sortArray(arrayString, algorithm, null)).withSelfRel();
        response.add(selfLink);
    }

    @ExceptionHandler(InvalidAlgorithmException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidAlgorithmException(InvalidAlgorithmException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(InvalidArrayFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidArrayFormatException(InvalidArrayFormatException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception e) {
        return new ErrorResponse("An unexpected error occurred: " + e.getMessage());
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
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




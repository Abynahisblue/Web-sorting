package com.example.webapp.controller;

import com.example.webapp.exception.InvalidAlgorithmException;
import com.example.webapp.exception.InvalidArrayFormatException;
import com.example.webapp.model.RequestModel;
import com.example.webapp.model.SortResponse;
import com.example.webapp.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/sort")
public class ApiController {
    @Autowired
    private SortService sortService;

    @PostMapping("/array")
    public SortResponse sortArray(@RequestBody RequestModel requestModel) {
        int[] array;
        try {
            array = Arrays.stream(requestModel.getArray().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new InvalidArrayFormatException("Invalid array format. Please provide a comma-separated list of integers.");
        }

        int[] sortedArray = switch (requestModel.getAlgorithm().toLowerCase()) {
            case "heap" -> sortService.heapSort(array);
            case "radix" -> sortService.radixSort(array);
            case "bucket" -> sortService.bucketSort(array);
            case "merge" -> sortService.mergeSort(array);
            case "quick" -> sortService.quickSort(array);
            default ->
                    throw new InvalidAlgorithmException("Invalid sorting algorithm. Supported algorithms are: heap, radix, bucket, merge, quick.");
        };

        SortResponse response = new SortResponse(sortedArray);
        EntityModel<SortResponse> resource = EntityModel.of(response);

        addHateoasLinks(resource, requestModel);

        return response;
    }

    private void addHateoasLinks(EntityModel<SortResponse> resource, RequestModel requestModel) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiController.class)
                .sortArray(requestModel)).withSelfRel();
        resource.add(selfLink);
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

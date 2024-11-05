package com.redhat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessDatFileBean {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessDatFileBean.class);

    private String displayMessage = "";

    private String examAnswersString;

    private boolean uploadSuccessful = false;

    private List<String> errors = null;

    private UploadedFile getServletFileUpload() throws ServletException, IOException {

        UploadedFile uploadedFile = null;

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        Collection<Part> parts = request.getParts();
        for (Part p : parts) {
            // this is the part that contains the uploaded file
            if ("textfile".equals(p.getName()) && p.getSubmittedFileName() != null && p.getSize() > 0) {
                uploadedFile = new UploadedFile();
                uploadedFile.setName(p.getSubmittedFileName());
                uploadedFile.setMime(p.getContentType());
                uploadedFile.setLength(p.getSize());
                uploadedFile.setData(IOUtils.toString(p.getInputStream(), StandardCharsets.UTF_8));

                return uploadedFile;
            }
        }

        return uploadedFile;
    }

    public String uploadFile() {

        UploadedFile uploadedFile = null;
        try {
            uploadedFile = getServletFileUpload();
            if (uploadedFile == null) {
                setDisplayMessage("Please Select one Grade Sheet File.");
                setUploadSuccessful(false);

                return null;
            }
        } catch (IOException | ServletException ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }

        setUploadSuccessful(true);
        displayMessage = uploadedFile.getName() + " was uploaded!";

        return "success";
    }

    public String clearUploadData() {

        return null;
    }

    public String getDisplayMessage() {

        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {

        this.displayMessage = displayMessage;
    }

    public boolean isUploadSuccessful() {

        return uploadSuccessful;
    }

    public void setUploadSuccessful(boolean uploadSuccessful) {

        this.uploadSuccessful = uploadSuccessful;
    }

    public List<String> getErrors() {

        return errors;
    }

    public void setErrors(ArrayList<String> errors) {

        this.errors = errors;
    }

    public String getExamAnswersString() {

        return examAnswersString;
    }

    public void setExamAnswersString(String examAnswersString) {

        this.examAnswersString = examAnswersString;
    }

}
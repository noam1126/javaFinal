package controllers;

import networking.Request;
import networking.Response;

public interface Controller {
    Response handleRequest(Request request);
}

package dev.dommi.gameserver.backend.plugin.api.services.report;

import dev.dommi.gameserver.backend.adapter.api.report.Report;
import dev.dommi.gameserver.backend.adapter.api.report.ReportService;
import dev.dommi.gameserver.backend.adapter.api.report.ReportType;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.plugin.api.server.APIServer;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;

public class ReportController {


    private static final String REPORT_ID = "reportId";
    private final ReportService reportService;

    public ReportController(ReportRepository reportRepository) {
        reportService = new ReportService(reportRepository);
    }

    @OpenApi(
            summary = "Report user",
            operationId = "reportUser",
            path = "/report",
            method = HttpMethod.POST,
            tags = {"Report"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = ReportUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public void report(Context ctx) {
        ReportUserRequest request = ctx.bodyAsClass(ReportUserRequest.class);
        int userId = APIServer.getUserIDFromRequestToken(ctx);
        reportService.reportUser(userId, request.reportedUserId, request.reason, request.reportTypeId);
        ctx.status(201);
    }

    @OpenApi(
            summary = "Update report status",
            operationId = "updateReportStatus",
            path = "/admin/reports/:" + REPORT_ID,
            method = HttpMethod.POST,
            tags = {"Report"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = UpdateReportStatusRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public void updateReportStatus(Context ctx) {
        UpdateReportStatusRequest request = ctx.bodyAsClass(UpdateReportStatusRequest.class);
        reportService.updateReportStatus(validPathParamReportId(ctx), request.status);
        ctx.status(201);
    }

    @OpenApi(
            summary = "Get a report",
            operationId = "getReport",
            path = "/admin/reports/:" + REPORT_ID,
            method = HttpMethod.GET,
            tags = {"Report"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Report.class)})
            }
    )
    public void getOne(Context ctx) {
        ctx.json(reportService.getReport(validPathParamReportId(ctx)));
    }

    @OpenApi(
            summary = "Get all reports",
            operationId = "getAllReports",
            path = "/admin/reports",
            method = HttpMethod.GET,
            tags = {"Report"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            queryParams = {
                    @OpenApiParam(name = "createdBy", required = false, description = "returns all Reports created by the User", type = Integer.class),
                    @OpenApiParam(name = "reportsFor", required = false, description = "returns all Reports that a User got", type = Integer.class)
            },
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Report[].class)})
            }
    )
    public void getAll(Context ctx) {
        Integer createdBy = ctx.queryParam("createdBy", Integer.class).getOrNull();
        Integer reportsFor = ctx.queryParam("reportsFor", Integer.class).getOrNull();
        if (createdBy != null && createdBy >= 0) {
            ctx.json(reportService.getReportsCreatedBy(createdBy));
        } else if (reportsFor != null && reportsFor >= 0) {
            ctx.json(reportService.getReportsFor(reportsFor));
        } else {
            ctx.json(reportService.getAllReports());
        }

    }

    @OpenApi(
            summary = "Get all report types",
            operationId = "getAllReportTypes",
            path = "/admin/reports/types",
            method = HttpMethod.GET,
            tags = {"Report"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = ReportType[].class)})
            }
    )
    public void getTypes(Context ctx) {
        ctx.json(reportService.getReportTypes());
    }

    private int validPathParamReportId(Context ctx) {
        return ctx.pathParam(REPORT_ID, Integer.class).check(id -> id > 0).get();
    }


}

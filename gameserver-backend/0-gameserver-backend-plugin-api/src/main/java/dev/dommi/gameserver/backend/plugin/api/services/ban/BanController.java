package dev.dommi.gameserver.backend.plugin.api.services.ban;

import dev.dommi.gameserver.backend.adapter.api.ban.Ban;
import dev.dommi.gameserver.backend.adapter.api.ban.BanService;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.plugin.api.server.APIServer;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

public class BanController {

    private static final Logger logger = Logger.getLogger(BanController.class.getName());
    private static final String BAN_ID = "banId";

    private final BanService banService;

    public BanController(BanRepository banRepository, UserRepository userRepository) {
        banService = new BanService(banRepository, userRepository);
    }

    @OpenApi(
            summary = "Ban user",
            operationId = "banUser",
            path = "/admin/bans",
            method = HttpMethod.POST,
            tags = {"Ban"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = BanUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public void ban(Context ctx) {
        BanUserRequest request = ctx.bodyAsClass(BanUserRequest.class);
        int userId = APIServer.getUserIDFromRequestToken(ctx);
        if (userId >= 0) {
            banService.banUser(request.userId, userId, request.reason, request.until);
            ctx.status(201);
        } else ctx.status(400).json(new BadRequestResponse("No valid UserId in token found!"));
    }

    @OpenApi(
            summary = "Update Ban",
            operationId = "updateBan",
            path = "/admin/bans/:" + BAN_ID,
            method = HttpMethod.POST,
            tags = {"Ban"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = UpdateBanRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public void updateBan(Context ctx) {
        UpdateBanRequest request = ctx.bodyAsClass(UpdateBanRequest.class);
        banService.update(validPathParamBanId(ctx), request.reason, request.until, request.active);
        ctx.status(201);
    }

    @OpenApi(
            summary = "Get a Ban",
            operationId = "getBan",
            path = "/admin/bans/:" + BAN_ID,
            method = HttpMethod.GET,
            tags = {"Ban"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Ban.class)})
            }
    )
    public void getOne(Context ctx) {
        ctx.json(banService.getOne(validPathParamBanId(ctx)));
    }

    @OpenApi(
            summary = "Get all Bans",
            operationId = "getAllBans",
            path = "/admin/bans",
            method = HttpMethod.GET,
            tags = {"Ban"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            queryParams = {
                    @OpenApiParam(name = "active", required = false, description = "returns all Bans that are active or all Bans that are Inactive", type = Boolean.class),
                    @OpenApiParam(name = "userId", required = false, description = "returns all Bans that a User got", type = Integer.class),
                    @OpenApiParam(name = "untilDate", required = false, description = "returns all Bans until this date", type = String.class)
            },
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Ban[].class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public void getAll(Context ctx) {
        Boolean active = ctx.queryParam("active", Boolean.class).getOrNull();
        Integer userId = ctx.queryParam("userId", Integer.class).getOrNull();
        String untilDate = ctx.queryParam("untilDate", String.class).getOrNull();
        Date date = null;
        try {
            if (untilDate != null && !untilDate.isEmpty()) {
                date = DateFormat.getInstance().parse(untilDate);
            }
            if (date != null && userId != null && userId >= 0) {
                ctx.json(banService.getAll(userId, date));
            } else if (userId != null && userId >= 0) {
                ctx.json(banService.getAll(userId));
            } else if (date != null) {
                ctx.json(banService.getAll(date));
            } else if (active != null) {
                ctx.json(banService.getAll(active));
            } else {
                ctx.json(banService.getAll());
            }
        } catch (ParseException e) {
            ctx.status(400).json(new BadRequestResponse("Could not parse Date!"));
        }
    }

    private int validPathParamBanId(Context ctx) {
        return ctx.pathParam(BAN_ID, Integer.class).check(id -> id > 0).get();
    }

}

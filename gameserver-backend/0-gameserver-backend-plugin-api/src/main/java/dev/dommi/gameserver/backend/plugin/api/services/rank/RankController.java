package dev.dommi.gameserver.backend.plugin.api.services.rank;

import dev.dommi.gameserver.backend.adapter.api.rank.Rank;
import dev.dommi.gameserver.backend.adapter.api.rank.RankService;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.openapi.annotations.*;

public class RankController {
    @OpenApi(
            summary = "Grant rank",
            operationId = "grantRank",
            path = "/admin/ranks/grant",
            method = HttpMethod.POST,
            tags = {"Rank"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = GrantRankRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public static void grant(Context ctx) {
        GrantRankRequest request = ctx.bodyAsClass(GrantRankRequest.class);
        try {
            RankService.grantRankTo(request.userId, request.rank);
            ctx.status(201);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
        }

    }

    @OpenApi(
            summary = "Revoke rank",
            operationId = "revokeRank",
            path = "/admin/ranks/revoke",
            method = HttpMethod.POST,
            tags = {"Rank"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = RevokeRankRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "401", content = {@OpenApiContent(from = UnauthorizedResponse.class)})
            }
    )
    public static void revoke(Context ctx) {
        RevokeRankRequest request = ctx.bodyAsClass(RevokeRankRequest.class);
        RankService.revokeRankFrom(request.userId);
        ctx.status(201);
    }

    @OpenApi(
            summary = "Get all ranks",
            operationId = "getAllRanks",
            path = "/admin/ranks",
            method = HttpMethod.GET,
            tags = {"Rank"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Rank[].class)})
            }
    )
    public static void getAll(Context ctx) {
        ctx.json(RankService.getAll());
    }

}

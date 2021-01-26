package dev.dommi.gameserver.backend.plugin.api.services.rank;

import dev.dommi.gameserver.backend.adapter.api.rank.Rank;
import dev.dommi.gameserver.backend.adapter.api.rank.RankService;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.openapi.annotations.*;

public class RankController {

    private final RankService rankService;

    public RankController(RankRepository rankRepository, UserRepository userRepository) {
        rankService = new RankService(rankRepository, userRepository);
    }

    @OpenApi(
            summary = "Grant rank",
            operationId = "grantRank",
            path = "/admin/ranks/grant",
            method = HttpMethod.POST,
            tags = {"Rank"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = GrantRankRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public void grant(Context ctx) {
        GrantRankRequest request = ctx.bodyAsClass(GrantRankRequest.class);
        try {
            rankService.grantRankTo(request.userId, request.rank);
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
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = RevokeRankRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "401", content = {@OpenApiContent(from = UnauthorizedResponse.class)})
            }
    )
    public void revoke(Context ctx) {
        RevokeRankRequest request = ctx.bodyAsClass(RevokeRankRequest.class);
        rankService.revokeRankFrom(request.userId);
        ctx.status(201);
    }

    @OpenApi(
            summary = "Get all ranks",
            operationId = "getAllRanks",
            path = "/admin/ranks",
            method = HttpMethod.GET,
            tags = {"Rank"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Rank[].class)})
            }
    )
    public void getAll(Context ctx) {
        ctx.json(rankService.getAll());
    }

}

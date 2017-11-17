/**
 * Copyright 2017 BlazeMeter Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blazemeter.api.explorer;

import com.blazemeter.api.explorer.base.BZAObject;
import com.blazemeter.api.utils.BlazeMeterUtils;
import net.sf.json.JSONObject;

import java.io.IOException;

public class Master extends BZAObject {

    public Master(BlazeMeterUtils utils, String id, String name) {
        super(utils, id, name);
    }

    /**
     * Makes a private user report public
     * @return public link to the report
     */
    public String makeReportPublic() throws IOException {
        String uri = utils.getAddress() + String.format("/api/v4/masters/%s/public-token", getId());
        JSONObject obj = new JSONObject();
        obj.put("publicToken", "None");
        JSONObject response = utils.execute(utils.createPost(uri, obj.toString()));

        return utils.getAddress() + String.format("/app/?public-token=%s#/masters/%s/summary",
                extractPublicToken(response.getJSONObject("result")), getId());
    }

    /**
     * Makes get junit report
     * @return junit report as a string
     */
    public String junitReport() throws IOException {
        String uri = utils.getAddress() + String.format("/api/v4/masters/%s/reports/thresholds?format=junit", getId());
        return utils.executeRequest(utils.createGet(uri));
    }



    private String extractPublicToken(JSONObject result) {
        return result.getString("publicToken");
    }

    public static Master fromJSON(BlazeMeterUtils utils, JSONObject obj) {
        return new Master(utils, obj.getString("id"), obj.getString("name"));
    }
}

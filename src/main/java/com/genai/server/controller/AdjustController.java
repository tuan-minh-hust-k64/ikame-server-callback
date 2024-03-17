package com.genai.server.controller;

import com.genai.server.gcp.PubSubHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/api/adjust")
public class AdjustController {
    private final PubSubHelper pubSubHelper;

    public AdjustController(PubSubHelper pubSubHelper) {
        this.pubSubHelper = pubSubHelper;
    }

    @GetMapping(value = "/mobile_attribution")
    public ResponseEntity<?> globalTrigger(
            @RequestParam(required=false, defaultValue = "null") String gps_adid,
            @RequestParam(required=false, defaultValue = "null") String idfv,
            @RequestParam(required=false, defaultValue = "null") String app_id,
            @RequestParam(required=false, defaultValue = "null") String installed_at,
            @RequestParam(required=false, defaultValue = "null") String reporting_revenue,
            @RequestParam(required=false, defaultValue = "null") String store,
            @RequestParam(required=false, defaultValue = "null") String app_name,
            @RequestParam(required=false, defaultValue = "null") String network_name,
            @RequestParam(required=false, defaultValue = "null") String campaign_name,
            @RequestParam(required=false, defaultValue = "null") String adgroup_name,
            @RequestParam(required=false, defaultValue = "null") String creative_name,
            @RequestParam(required=false, defaultValue = "null") String is_organic,
            @RequestParam(required=false, defaultValue = "null") String install_begin_time,
            @RequestParam(required=false, defaultValue = "null") String uninstalled_at,
            @RequestParam(required=false, defaultValue = "null") String reinstalled_at,
            @RequestParam(required=false, defaultValue = "null") String idfa,
            @RequestParam(required=false, defaultValue = "null") String country,
            @RequestParam(required=false, defaultValue = "null") String city,
            @RequestParam(required=false, defaultValue = "null") String event_name,
            @RequestParam(required=false, defaultValue = "null") String activity_kind,
            @RequestParam(required=false, defaultValue = "null") String random_user_id,
            @RequestParam(required=false, defaultValue = "null") String created_at,
            @RequestParam(required=false, defaultValue = "null") String att_status,
            @RequestParam(required=false, defaultValue = "null") String outdated_tracker,
            @RequestParam(required=false, defaultValue = "null") String outdated_tracker_name,
            @RequestParam(required=false, defaultValue = "null") String attribution_updated_at,
            @RequestParam(required=false, defaultValue = "null") String sk_ts,
            @RequestParam(required=false, defaultValue = "null") String sk_payload,
            @RequestParam(required=false, defaultValue = "null") String sk_version,
            @RequestParam(required=false, defaultValue = "null") String sk_network_id,
            @RequestParam(required=false, defaultValue = "null") String sk_campaign_id,
            @RequestParam(required=false, defaultValue = "null") String sk_transaction_id,
            @RequestParam(required=false, defaultValue = "null") String sk_app_id,
            @RequestParam(required=false, defaultValue = "null") String sk_attribution_signature,
            @RequestParam(required=false, defaultValue = "null") String sk_redownload,
            @RequestParam(required=false, defaultValue = "null") String sk_source_app_id,
            @RequestParam(required=false, defaultValue = "null") String sk_conversion_value,
            @RequestParam(required=false, defaultValue = "null") String sk_invalid_signature,
            @RequestParam(required=false, defaultValue = "null") String sk_fidelity_type,
            @RequestParam(required=false, defaultValue = "null") String sk_did_win,
            @RequestParam(required=false, defaultValue = "null") String partner,
            @RequestParam(required=false, defaultValue = "null") String ip_address,
            @RequestParam(required=false, defaultValue = "null") String user_agent,
            @RequestParam(required=false, defaultValue = "null") String tracker,
            @RequestParam(required=false, defaultValue = "null") String fb_install_referrer_campaign_group_id,
            @RequestParam(required=false, defaultValue = "null") String fb_install_referrer_campaign_group_name,
            @RequestParam(required=false, defaultValue = "null") String fb_install_referrer_campaign_id,
            @RequestParam(required=false, defaultValue = "null") String fb_install_referrer_campaign_name,
            @RequestParam(required=false, defaultValue = "null") String fb_install_referrer_adgroup_id,
            @RequestParam(required=false, defaultValue = "null") String fb_install_referrer_adgroup_name,
            @RequestParam(required=false, defaultValue = "null") String ad_mediation_platform,
            @RequestParam(required=false, defaultValue = "null") String device_type,
            @RequestParam(required=false, defaultValue = "null") String device_name,
            @RequestParam(required=false, defaultValue = "null") String sdk_version,
            @RequestParam(required=false, defaultValue = "null") String os_version,
            @RequestParam(required=false, defaultValue = "null") String environment,
            @RequestParam(required=false, defaultValue = "null") String language,
            @RequestParam(required=false, defaultValue = "null") String app_version,
            @RequestParam(required=false, defaultValue = "null") String partner_parameters,
            @RequestParam(required=false, defaultValue = "null") String google_ads_campaign_id,
            @RequestParam(required=false, defaultValue = "null") String ad_revenue_network,
            @RequestParam(required=false, defaultValue = "null") String ad_revenue_unit,
            @RequestParam(required=false, defaultValue = "null") String rejection_reason,
            @RequestParam(required=false, defaultValue = "null") String install_finish_time,
            @RequestParam(required=false, defaultValue = "null") String api_level,
            @RequestParam(required=false, defaultValue = "null") String os_name,
            @RequestParam(required=false, defaultValue = "null") String lifetime_session_count,
            @RequestParam(required=false, defaultValue = "null") String subscription_event_type,
            @RequestParam(required=false, defaultValue = "null") String subscription_event_subtype,
            @RequestParam(required=false, defaultValue = "null") String subscription_purchased_at,
            @RequestParam(required=false, defaultValue = "null") String subscription_expiration_time,
            @RequestParam(required=false, defaultValue = "null") String subscription_cancelled_at,
            @RequestParam(required=false, defaultValue = "null") String subscription_transaction_id,
            @RequestParam(required=false, defaultValue = "null") String subscription_original_transaction_id,
            @RequestParam(required=false, defaultValue = "null") String subscription_expired_transaction_id,
            @RequestParam(required=false, defaultValue = "null") String subscription_environment,
            @RequestParam(required=false, defaultValue = "null") String subscription_product_id,
            @RequestParam(required=false, defaultValue = "null") String subscription_sales_region,
            @RequestParam(required=false, defaultValue = "null") String subscription_store,
            @RequestParam(required=false, defaultValue = "null") String reftag,
            @RequestParam(required=false, defaultValue = "null") String reftags
    ) {
        Map<String, ?> data = new HashMap<>() {{
            put("gps_adid", gps_adid);
            put("idfv", idfv);
            put("app_id", app_id);
            put("installed_at", installed_at);
            put("reporting_revenue", reporting_revenue);
            put("store", store);
            put("app_name", app_name);
            put("network_name", network_name);
            put("campaign_name", campaign_name);
            put("adgroup_name", adgroup_name);
            put("creative_name", creative_name);
            put("is_organic", is_organic);
            put("install_begin_time", install_begin_time);
            put("uninstalled_at", uninstalled_at);
            put("reinstalled_at", reinstalled_at);
            put("idfa", idfa);
            put("country", country);
            put("city", city);
            put("event_name", event_name);
            put("activity_kind", activity_kind);
            put("random_user_id", random_user_id);
            put("created_at", created_at);
            put("att_status", att_status);
            put("outdated_tracker", outdated_tracker);
            put("outdated_tracker_name", outdated_tracker_name);
            put("attribution_updated_at", attribution_updated_at);
            put("sk_ts", sk_ts);
            put("sk_payload", sk_payload);
            put("sk_version", sk_version);
            put("sk_network_id", sk_network_id);
            put("sk_campaign_id", sk_campaign_id);
            put("sk_transaction_id", sk_transaction_id);
            put("sk_app_id", sk_app_id);
            put("sk_attribution_signature", sk_attribution_signature);
            put("sk_redownload", sk_redownload);
            put("sk_source_app_id", sk_source_app_id);
            put("sk_conversion_value", sk_conversion_value);
            put("sk_invalid_signature", sk_invalid_signature);
            put("sk_fidelity_type", sk_fidelity_type);
            put("sk_did_win", sk_did_win);
            put("partner", partner);
            put("ip_address", ip_address);
            put("user_agent", user_agent);
            put("tracker", tracker);
            put("fb_install_referrer_campaign_group_id", fb_install_referrer_campaign_group_id);
            put("fb_install_referrer_campaign_group_name", fb_install_referrer_campaign_group_name);
            put("fb_install_referrer_campaign_id", fb_install_referrer_campaign_id);
            put("fb_install_referrer_campaign_name", fb_install_referrer_campaign_name);
            put("fb_install_referrer_adgroup_id", fb_install_referrer_adgroup_id);
            put("fb_install_referrer_adgroup_name", fb_install_referrer_adgroup_name);
            put("ad_mediation_platform", ad_mediation_platform);
            put("device_type", device_type);
            put("device_name", device_name);
            put("api_level", api_level);
            put("sdk_version", sdk_version);
            put("os_version", os_version);
            put("environment", environment);
            put("language", language);
            put("app_version", app_version);
            put("partner_parameters", partner_parameters);
            put("google_ads_campaign_id", google_ads_campaign_id);
            put("ad_revenue_network", ad_revenue_network);
            put("ad_revenue_unit", ad_revenue_unit);
            put("rejection_reason", rejection_reason);
            put("install_finish_time", install_finish_time);
            put("os_name", os_name);
            put("lifetime_session_count", lifetime_session_count);
            put("subscription_event_type", subscription_event_type);
            put("subscription_event_subtype", subscription_event_subtype);
            put("subscription_purchased_at", subscription_purchased_at);
            put("subscription_expiration_time", subscription_expiration_time);
            put("subscription_cancelled_at", subscription_cancelled_at);
            put("subscription_transaction_id", subscription_transaction_id);
            put("subscription_original_transaction_id", subscription_original_transaction_id);
            put("subscription_expired_transaction_id", subscription_expired_transaction_id);
            put("subscription_environment", subscription_environment);
            put("subscription_product_id", subscription_product_id);
            put("subscription_sales_region", subscription_sales_region);
            put("subscription_store", subscription_store);
            put("reftag", reftag);
            put("reftags", reftags);
        }};
        Gson gson = new Gson();
        Type typeObject = new TypeToken<HashMap>() {}.getType();
        String gsonData = gson.toJson(data, typeObject);
        try {
            pubSubHelper.publishWithErrorHandlerExample("ikame-gem-ai-research", "test-1", gsonData);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("OK");
    }
}

package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Specify if system should track IPs of your employees
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class IPAddressConfig {
    private String trackIpAddress;
}

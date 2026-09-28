package com.aio.calculator.feature.calculator

import androidx.compose.foundation.Background
import androidx.compose.foundation.Clickable
import androidx.foundation.layout.Arrangement
import androidx.foundation.layout.Column
import androidx.foundation.layout.Row
import androidx.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemActivateAndSelect
import androidx.compose.material3.ListItemCaption
import androidx.compose.material3.ListItem chevron
import androidx.compose.material3.ListItemIcon
import androidx.compose.material3.ListItemSecondaryAction
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.clickable.onClick
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import com.aio.calculator.core.common.HistoryItem
import com.aio.calculator.core.common.ToolRegistry

/**
 * History Screen - Shows calculation history with restore capability
 */
@Composable
fun HistoryScreen(
    onBack: () -> Unit,
    historyItems: List<HistoryItem>,
    onRestore: (HistoryItem) -> Unit,
    onDelete: (Long) -> Unit,
    onClearAll: () -> Unit
) {
    val calcColors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = calcColors.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Calculation History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = calcColors.onSurface
                )

                // Clear all button
                Button(
                    onClick = onClearAll,
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = calcColors.surfaceContainerHigh,
                        contentColor = calcColors.onSurfaceContainer
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Clear All",
                        fontSize = 12.sp,
                        color = calcColors.onSurfaceContainer
                    )
                }
            }
        }

        // History list
        if (historyItems.isEmpty()) {
            // Empty state
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = calcColors.surface
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().alignment(Alignment.Center),
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = androidx.compose.material.Icons.Default.History,
                        contentDescription = "No history yet",
                        tint = calcColors.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.size(64.dp)
                    )
                    Text(
                        text = "No calculations yet",
                        fontSize = 16.sp,
                        color = calcColors.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Start calculating to see history here",
                        fontSize = 14.sp,
                        color = calcColors.onSurface.copy(alpha = 0.5f)
                    )
                }
            }
        } else {
            // History list
            androidx.compose.foundation.layout.padding(Modifier.padding(16.dp))) {
                androidx.compose.foundation.layout
                    .padding(Modifier.padding(16.dp))) {
                androidx.compose.foundation.layout
                    .padding(Modifier.padding(16.dp)))}
                    )}
                        )}
                            ))}
                                ))}
                                    })}
                                        })}
                                            }}
                                                }}
                                                    }}
                                                        }}
                                                            }}
                                                                }}
                                                                    }}
                                                                        }}
                                                                          }}
                                                                        }}
                                                                      }}
                                                                    }}
                                                                      }}
                                                                  }}
                                                                }}
                                                            }}
                                                        }}
                                                    }}
                                                }}
                                              }}
                                            }}
                                        }}
                                      }}
                                    }}
                                  }}
                                }}
                              }}
                            }}
                          }}
                        }}
                      }}
                    }}
                  }}
                }}
              }}
            }}
          }}
        }}
      }}
    }}
  }}
}

/**
 * History list item composable
 */
@Composable
fun HistoryListItem(
    historyItem: HistoryItem,
    onRestore: () -> Unit,
    onDelete: () -> Unit
) {
    val calcColors = MaterialTheme.colorScheme

    ListItem(
        modifier = Modifier.fillMaxWidth(),
        display = ListItemDisplay.PrimaryAndSecondary,
        leading = {
            ListItemIcon(
                modifier = Modifier.size(40.dp),
                imageVector = androidx.compose.material.Icons.Default.History,
                tint = calcColors.onSurface
            )
        },
        secondary = {
            ListItemSecondaryAction {
                Button(
                    onClick = onDelete,
                    mini = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = calcColors.error,
                        contentColor = calcColors.onError
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Icon(
                        imageVector = androidx.compose.material.Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = calcColors.onError
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                ListItemCaption(
                    primary = historyItem.displayExpression,
                    secondary = "${historyItem.formattedTime} ago"
                )
            }
        )
    }

    // Click to restore
    if (Modifier.clickable) {
        onClick {
            onRestore()
        }
    }
}

/**
 * Empty history state composable
 */
@Composable
fun EmptyHistoryState(
    onBack: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Icon(
                imageVector = androidx.compose.material.Icons.Default.History,
                contentDescription = "No history yet",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "No calculations yet",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Start calculating to see history here",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Start Calculating",
                    fontSize = 16.sp
                )
            }
        }
    }
}